package com.cricfant.service;

import com.cricfant.constant.PowerType;
import com.cricfant.dto.PlayerDto;
import com.cricfant.dto.PointsDto;
import com.cricfant.dto.SquadDto;
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
            if (lockedInSquad != null && lockedInSquad.size()!=0) { // if not new squad
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
        SquadDto retObj = getFromSquad(squad, null);
        retObj.setSubsLeft(nonLockedInSubsLeft);
        retObj.setForMatchId(squadDto.getForMatchId());
        return retObj;
    }

    private void validateSquad(SquadDto squad) {
        Set<PlayerDto> players = squad.getPlayers();
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
            squadDtos.add(getFromSquad(squad, null));
        });
        return squadDtos;
    }

    @SuppressWarnings("Duplicates")
    private SquadDto getFromSquad(Squad squad, Set<Lockin> lockins) {
        SquadDto s = new SquadDto();
        s.setId(squad.getId());
        s.setName(squad.getName());
        s.setTournamentId(squad.getTournament().getId());
        s.setSubsLeft(squad.getSubsLeft());
        Map<Integer, String> leagues = new HashMap<>();
        if (squad.getLeagues() != null) {
            squad.getLeagues().forEach(league -> {
                leagues.put(league.getId(), league.getName());
            });
        }
        s.setLeagues(leagues);
        Set<PlayerDto> players = new HashSet<>();
        if (lockins != null) {
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
                players.add(p);
            });
        } else {
            if (squad.getSquadPlayers() != null) {
                squad.getSquadPlayers().forEach(squadPlayer -> {
                    PlayerDto p = new PlayerDto();
                    Player player = squadPlayer.getPlayer();
                    p.setId(player.getId());
                    p.setName(player.getName());
                    p.setPowerType(squadPlayer.getPowerType());
                    p.setSkill(squadPlayer.getPlayer().getSkill());
                    players.add(p);
                });
            }
        }
        s.setPlayers(players);
        return s;
    }

    public SquadDto getSquad(Integer squadId, Integer userId) {
        Squad squad = squadRepository.findById(squadId).orElseThrow(() ->
                new IllegalArgumentException("no such squad"));
        if (squad.getUser().getId().equals(userId)) {
            return getFromSquad(squad, null);
        } else {
            Optional<Match> optionalMatch = matchService.getLastMatch(squad.getTournament().getId());
            if (!optionalMatch.isPresent()) {
                return getFromSquad(squad, null);
            }
            Match lastMatch = optionalMatch.get();
            Set<Lockin> lastMatchLockins = lockinRepository
                    .findAllByMatchIdAndSquadId(lastMatch.getId(), squad.getId());
            return getFromSquad(squad, lastMatchLockins);
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
        Set<Lockin> lockins = lockinRepository.findAllByMatchIdAndSquadId(matchId, squadId);
        Squad squad = squadRepository.findById(squadId).get();
        SquadDto squadDto = getFromSquad(squad, lockins);
        return squadDto;
    }
}
