package com.cricfant.service;

import com.cricfant.constant.PowerType;
import com.cricfant.dto.*;
import com.cricfant.model.*;
import com.cricfant.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class SquadService {
    @Autowired
    MatchService matchService;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    MatchRepository matchRepository;
    @Autowired
    LockinRepository lockinRepository;
    @Autowired
    SquadRepository squadRepository;
    @Autowired
    SquadPlayerRepository squadPlayerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TournamentRepository tournamentRepository;
    @Autowired
    MatchPerfRepository matchPerfRepository;

    public SquadDto setSquad(Integer squadId, SquadDto squadDto, Integer userId) {
        Optional<Squad> oSquad = squadRepository.findById(squadId);
        if (!oSquad.isPresent()) {
            throw new IllegalArgumentException("no such squad");
        }
        Squad squad = oSquad.get();
        if (!squad.getUser().getId().equals(userId)) {
            throw new IllegalStateException("user does not own squad");
        }
        Tournament tournament = squad.getTournament();
        Optional<Match> oNextMatch = matchService.getNextMatch(
                tournament.getId());
        if (oNextMatch.isPresent()) {
            Match nextMatch = oNextMatch.get();
            if (!nextMatch.getId().equals(squadDto.getForMatchId())) {
                throw new IllegalArgumentException("Can't pick team for: " + squadDto.getForMatchId() + ". Next match is: " + nextMatch.getId());
            }
        } else {
            throw new IllegalStateException("Tournament is over");
        }
        List<SquadPlayer> squadPlayers = new ArrayList<>();
        validatePlayers(squadDto, tournament);
        validateSquad(squadDto);
        squadDto.getPlayers().forEach(player -> {
            SquadPlayer sPlayer = new SquadPlayer();
            sPlayer.setPlayer(playerRepository.findById(player.getId()).get());
            sPlayer.setPowerType(player.getPowerType());
            sPlayer.setSquad(squad);
            squadPlayers.add(sPlayer);
        });
        Integer freeSubs = tournament.getFreeSubs();
        Optional<Match> oLastMatch = matchService.getLastMatch(
                tournament.getId());
        Integer nonLockedInSubsLeft = squad.getSubsLeft();
        Boolean isUnlimitedSubsPeriod = tournament.getUnlimitedSubs();
        if (!isUnlimitedSubsPeriod && oLastMatch.isPresent()) {
            Match lastMatch = oLastMatch.get();
            Set<Lockin> lockedInSquad = lockinRepository
                    .findAllByMatchIdAndSquadId(lastMatch.getId(), squad.getId());
            if (lockedInSquad != null && lockedInSquad.size() != 0) { // if not new squad
                Integer subs = calculateSubs(lockedInSquad, squadPlayers);
                if ((nonLockedInSubsLeft + freeSubs) < subs) {
                    throw new IllegalStateException("not enough subs: "
                            + (nonLockedInSubsLeft + freeSubs) + "<" + subs);
                }
                nonLockedInSubsLeft = Integer.min(tournament.getTotalSubs(),
                        nonLockedInSubsLeft - (Integer.max(0, subs - freeSubs)));
            }
        }
        if (squad.getSquadPlayers() != null && squad.getSquadPlayers().size() > 0) {
            squadPlayerRepository.deleteAll(squad.getSquadPlayers());
        }
        squadPlayerRepository.flush();
        squadPlayerRepository.saveAll(squadPlayers);
        squadPlayerRepository.flush();
        SquadDto dto = getFromSquad(squad);
        dto.setPlayers(getPlayersFromSquad(squad));
        dto.setSubsLeft(nonLockedInSubsLeft);
        dto.setForMatchId(squadDto.getForMatchId());
        return dto;
    }

    private void validateSquad(SquadDto squad) {
        List<PlayerDto> players = squad.getPlayers();
        if (players.size() != 11) {
            throw new IllegalArgumentException("squad size: " + players);
        }
        Set<Integer> uniquePlayerIds = new HashSet<>();
        Map<Team, Integer> teamMap = new HashMap<>();
        int battingPPCount = 0;
        int bowlingPPCount = 0;
        int batCount = 0;
        int bowlCount = 0;
        int keeperCount = 0;
        int arCount = 0;

        for (PlayerDto player : players) {
            if (!uniquePlayerIds.add(player.getId())) {
                throw new IllegalArgumentException("duplicate player: " + player.getId());
            }
            if (player.getPowerType() != null && player.getPowerType().equals(PowerType.BATTING)) {
                battingPPCount++;
            }
            if (player.getPowerType() != null && player.getPowerType().equals(PowerType.BOWLING)) {
                bowlingPPCount++;
            }
            Player p = playerRepository.findById(player.getId()).get();
            Team team = p.getTeam();
            teamMap.putIfAbsent(team, 0);
            teamMap.put(team, teamMap.get(team) + 1);
            switch (p.getSkill()) {
                case BATSMAN:
                    batCount++;
                    break;
                case BOWLER:
                    bowlCount++;
                    break;
                case KEEPER:
                    keeperCount++;
                    break;
                case ALL_ROUNDER:
                    arCount++;
                    break;
            }

        }

        for (Team team : teamMap.keySet()) {
            if (teamMap.get(team) > 6) {
                throw new IllegalArgumentException("too many players from: " + team.getShortName());
            }
        }

        if (battingPPCount != 1 || bowlingPPCount != 1) {
            throw new IllegalArgumentException("battingPP: " + battingPPCount + " bowlingPP: " + bowlingPPCount);
        }
        if (keeperCount != 1) {
            throw new IllegalArgumentException("keeper count: " + keeperCount);
        }
        if (batCount < 3) {
            throw new IllegalArgumentException("batsman count: " + keeperCount);
        }
        if (bowlCount < 3) {
            throw new IllegalArgumentException("bowler count: " + keeperCount);
        }
        if (arCount < 1) {
            throw new IllegalArgumentException("allrounder count: " + keeperCount);
        }
    }

    private void validatePlayers(SquadDto squad, Tournament tournament) {
        List<Integer> validPlayerIds = new ArrayList<>();
        List<Integer> playerIds = squad.getPlayers().stream()
                .map(PlayerDto::getId)
                .collect(Collectors.toList());
        List<Team> teams = tournament.getTeams();
        teams.forEach(team -> {
            team.getPlayers()
                    .stream().mapToInt(value -> value.getId()).forEach(validPlayerIds::add);
        });
        if (!validPlayerIds.containsAll(playerIds)) {
            List<Integer> invalidPlayerIds = new ArrayList<>(playerIds);
            invalidPlayerIds.removeAll(validPlayerIds);
            throw new IllegalArgumentException("invalid players found: " + invalidPlayerIds.toString());
        }
    }

    public Integer getNonLockedInSubsLeft(Integer squadId) {
        Squad squad = squadRepository.findById(squadId).get();
        Integer lockedInSubsLeft = squad.getSubsLeft();
        Integer nonLockedInSubsLeft = lockedInSubsLeft;
        Tournament tournament = squad.getTournament();
        Optional<Match> oLastMatch = matchService.getLastMatch(
                tournament.getId());
        if (!oLastMatch.isPresent()) {
            log.debug("first match of tournament");
            return lockedInSubsLeft;
        }
        Boolean isUnlimitedSubsPeriod = tournament.getUnlimitedSubs();
        if (isUnlimitedSubsPeriod) {
            log.debug("unlimited subs allowed");
            return lockedInSubsLeft;
        }
        Match lastMatch = oLastMatch.get();
        Set<Lockin> lockedInSquad = lockinRepository
                .findAllByMatchIdAndSquadId(lastMatch.getId(), squad.getId());
        if ((lockedInSquad != null) && lockedInSquad.size() != 0) { // if not new squad
            Set<SquadPlayer> nonLockedInSquad = squad.getSquadPlayers();
            log.debug("nonLockedInSquad = " + nonLockedInSquad);
            Integer subsSinceLastLockin = calculateSubs(lockedInSquad, nonLockedInSquad);
            log.debug("subsSinceLastLockin = " + subsSinceLastLockin);
            Integer freeSubs = tournament.getFreeSubs();
            nonLockedInSubsLeft = Integer.min(
                    tournament.getTotalSubs(),
                    (lockedInSubsLeft -
                            (Integer.max(0, subsSinceLastLockin - freeSubs))));
        }
        return nonLockedInSubsLeft;
    }

    public Integer calculateSubs(Collection<Lockin> lockedInSquad,
                                 Collection<SquadPlayer> nonLockedInSquad) {
        List<Integer> currentSquadPlayerIds = lockedInSquad.stream()
                .map(csLockin -> csLockin.getPlayer().getId()).collect(Collectors.toList());
        log.debug("currentSquadPlayerIds = " + currentSquadPlayerIds);
        List<Integer> newSquadPlayerIds = nonLockedInSquad.stream()
                .map(mp -> mp.getPlayer().getId()).collect(Collectors.toList());
        log.debug("newSquadPlayerIds = " + newSquadPlayerIds);
        newSquadPlayerIds.removeAll(currentSquadPlayerIds);
        log.debug("newSquadPlayerIds = " + newSquadPlayerIds);
        return newSquadPlayerIds.size();
    }

    public List<SquadDto> getSquads(Integer userId) {
        User user = userRepository.findById(userId).get();
        List<SquadDto> squadDtos = new ArrayList<>();
        List<Squad> squads = user.getSquads();
        squads.forEach(squad -> {
            SquadDto dto = getFromSquad(squad);
            dto.setPlayers(getPlayersFromSquad(squad));
            squadDtos.add(dto);
        });
        return squadDtos;
    }

    public SquadDto getFromSquad(Squad squad) {
        SquadDto s = new SquadDto();
        s.setId(squad.getId());
        s.setName(squad.getName());
        s.setTournamentId(squad.getTournament().getId());
        s.setSubsLeft(squad.getSubsLeft());
        s.setPoints(squad.getPoints());
        s.setLastMatchPoints(getLastMatchPoints(squad));
        List<LeagueForSquadDto> leagues = new ArrayList<>();
        if (squad.getLeagues() != null) {
            squad.getLeagues().forEach(league -> {
                LeagueForSquadDto dto = new LeagueForSquadDto();
                dto.setId(league.getId());
                dto.setName(league.getName());
                int i = league.getSquads().indexOf(squad);
                dto.setRank(i + 1);
                leagues.add(dto);
            });
        }
        s.setLeagues(leagues);
        return s;
    }

    private Integer getLastMatchPoints(Squad squad) {
        Optional<Match> lastMatch = matchService.getLastMatch(squad.getTournament().getId());
        if (!lastMatch.isPresent()) {
            return 0;
        }
        Set<Lockin> lockins = lockinRepository
                .findAllByMatchIdAndSquadId(lastMatch.get().getId(), squad.getId());
        if (lockins == null || lockins.size() == 0) {
            return 0;
        }
        Integer totalPoints = lockins.stream().collect(Collectors.summingInt(lockin -> lockin.getBattingPoints()
                + lockin.getBowlingPoints()
                + lockin.getFieldingPoints() + lockin.getBonusPoints()));
        return totalPoints;
    }

    @SuppressWarnings("Duplicates")
    private List<PlayerDto> getPlayersPoints(Squad squad, Match match) {
        List<PlayerDto> players = new ArrayList<>();
        Set<Lockin> lockins = lockinRepository
                .findAllByMatchIdAndSquadId(match.getId(), squad.getId());
        lockins.forEach(lockin -> {
            PlayerDto p = new PlayerDto();
            Player player = lockin.getPlayer();
            p.setId(player.getId());
            p.setName(player.getName());
            p.setPowerType(lockin.getPowerType());
            p.setSkill(lockin.getPlayer().getSkill());
            PointsDto pDto = new PointsDto();
            Integer battingPoints = lockin.getBattingPoints() == null ? 0 : lockin.getBattingPoints();
            Integer bowlingPoints = lockin.getBowlingPoints() == null ? 0 : lockin.getBowlingPoints();
            Integer fieldingPoints = lockin.getFieldingPoints() == null ? 0 : lockin.getFieldingPoints();
            Integer bonusPoints = lockin.getBonusPoints() == null ? 0 : lockin.getBonusPoints();
            pDto.setBattingPoints(battingPoints);
            pDto.setBowlingPoints(bowlingPoints);
            pDto.setFieldingPoints(fieldingPoints);
            pDto.setBonusPoints(bonusPoints);
            pDto.setTotalPoints(battingPoints + bowlingPoints + fieldingPoints + bonusPoints);
            p.setPoints(pDto);
            TeamDto teamDto = new TeamDto();
            Team team = player.getTeam();
            teamDto.setId(team.getId());
            teamDto.setName(team.getName());
            teamDto.setShortName(team.getShortName());
            p.setTeam(teamDto);
            players.add(p);
        });
        players.sort(Comparator
                .comparing((PlayerDto playerDto) -> playerDto.getSkill().getId())
                .thenComparing(PlayerDto::getName)
        );
        return players;
    }

    @SuppressWarnings("Duplicates")
    private List<PlayerDto> getPlayersFromSquad(Squad squad) {
        List<PlayerDto> players = new ArrayList<>();
        if (squad.getSquadPlayers() != null) {
            squad.getSquadPlayers().forEach(squadPlayer -> {
                PlayerDto p = new PlayerDto();
                Player player = squadPlayer.getPlayer();
                p.setId(player.getId());
                p.setName(player.getName());
                p.setPowerType(squadPlayer.getPowerType());
                p.setSkill(squadPlayer.getPlayer().getSkill());
                TeamDto teamDto = new TeamDto();
                Team team = player.getTeam();
                teamDto.setId(team.getId());
                teamDto.setName(team.getName());
                teamDto.setShortName(team.getShortName());
                p.setTeam(teamDto);
                players.add(p);
            });
        }
        players.sort(Comparator
                .comparing((PlayerDto playerDto) -> playerDto.getSkill().getId())
                .thenComparing(PlayerDto::getName));
        return players;
    }

    public SquadDto getSquad(Integer squadId, Integer userId) {
        Squad squad = squadRepository.findById(squadId).orElseThrow(() ->
                new IllegalArgumentException("no such squad"));
        if (squad.getUser().getId().equals(userId)) {
            SquadDto dto = getFromSquad(squad);
            dto.setPlayers(getPlayersFromSquad(squad));
            return dto;
        } else {
            Optional<Match> optionalMatch = matchService.getLastMatch(squad.getTournament().getId());
            if (!optionalMatch.isPresent()) {
                SquadDto dto = getFromSquad(squad);
                dto.setPlayers(getPlayersFromSquad(squad));
                return dto;
            }
            SquadDto dto = getFromSquad(squad);
            dto.setPlayers(getPlayersPoints(squad, optionalMatch.get()));
            return dto;
        }
    }

    public SquadDto createSquad(Integer userId, SquadDto squadDto) {
        Squad s = new Squad();
        s.setName(squadDto.getName());
        Tournament tournament = tournamentRepository.findById(squadDto.getTournamentId()).get();
        s.setTournament(tournament);
        s.setSubsLeft(tournament.getTotalSubs());
        s.setPoints(0);
        s.setUser(userRepository.findById(userId).get());
        Squad save = squadRepository.save(s);
        SquadDto dto = setSquad(save.getId(), squadDto, userId);
        return dto;
    }

    public SquadDto getSquadHistory(Integer matchId, Integer squadId) {
        Squad squad = squadRepository.findById(squadId).orElseThrow(() ->
                new IllegalArgumentException("no such squad"));
        Match match = matchRepository.findById(matchId).orElseThrow(() ->
                new IllegalArgumentException("no such match"));
        SquadDto dto = getFromSquad(squad);
        dto.setPlayers(getPlayersPoints(squad, match));
        return dto;
    }
}
