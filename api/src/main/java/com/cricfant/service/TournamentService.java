package com.cricfant.service;

import com.cricfant.constant.PowerType;
import com.cricfant.dto.MatchDto;
import com.cricfant.dto.TournamentDto;
import com.cricfant.model.*;
import com.cricfant.repository.LockinRepository;
import com.cricfant.repository.MatchPerfRepository;
import com.cricfant.repository.TournamentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;
    @Autowired
    private MatchService matchService;
    @Autowired
    private SquadService squadService;
    @Autowired
    private LockinRepository lockinRepository;
    @Autowired
    private MatchPerfRepository matchPerfRepository;

    public void lockin(Integer tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        Optional<Match> oMatchToLockin = matchService.getNextMatch(tournamentId);
        if (!oMatchToLockin.isPresent()) {
            log.warn("no more matches to lockin");
        }
        Match matchToLockin = oMatchToLockin.get();
        log.info("locking in match: " + matchToLockin.getId());
        lockinRepository.deleteAll(matchToLockin.getLockins());
        lockinRepository.flush();
        Collection<Squad> squads = tournament.getSquads();
        squads.forEach(squad -> {
            Integer subsLeft = squadService.getNonLockedInSubsLeft(squad.getId());
            squad.setSubsLeft(subsLeft);
            Collection<SquadPlayer> squadPlayers = squad.getSquadPlayers();
            squadPlayers.forEach(squadPlayer -> {
                Lockin l = new Lockin();
                l.setSquad(squad);
                l.setTournamentTeamPlayer(squadPlayer.getTournamentTeamPlayer());
                l.setMatch(matchToLockin);
                l.setPowerType(squadPlayer.getPowerType());
                lockinRepository.save(l);
            });
        });
        matchToLockin.setLockedIn(true);
        log.info("locked in match: " + matchToLockin.getId());
    }

    // TODO implement rollback for points calculation rollBackPointsCalculation(matchId)
    public void calculatePoints(Integer tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        Collection<Squad> squads = tournament.getSquads();
        Match lastMatch = matchService.getLastMatch(tournamentId).get();
        squads.forEach(squad -> {
            Integer totalPoints = setLockinPoints(lastMatch, squad);
            squad.setPoints(squad.getPoints() + totalPoints);
        });
        lastMatch.setPointsUpdated(true);
    }

    private Integer setLockinPoints(Match match, Squad squad) {
        Set<Lockin> lockins = lockinRepository.findAllByMatchIdAndSquadId(match.getId(), squad.getId());
        Integer totalPoints = 0;
        for (Lockin lockin : lockins) {
            TournamentTeamPlayer tournamentTeamPlayer = lockin.getTournamentTeamPlayer();
            MatchPerformance mp = matchPerfRepository.findByTournamentTeamPlayerIdAndMatchId(
                    tournamentTeamPlayer.getId(), lockin.getMatch().getId());
            if (mp == null) { // player did not play in match
                continue;
            }
            Integer battingPoints = mp.getBattingPoints() == null ? 0 : mp.getBattingPoints();
            Integer bowlingPoints = mp.getBowlingPoints() == null ? 0 : mp.getBowlingPoints();
            Integer fieldingPoints = mp.getFieldingPoints() == null ? 0 : mp.getFieldingPoints();
            Integer bonusPoints = mp.getBonusPoints() == null ? 0 : mp.getBonusPoints();
            int playerPoints = battingPoints + bowlingPoints + fieldingPoints + bonusPoints;
            tournamentTeamPlayer.setPoints(tournamentTeamPlayer.getPoints() + playerPoints);
            PowerType powerType = lockin.getPowerType();
            battingPoints = (powerType != null && powerType.equals(PowerType.BATTING)) ? 2 * battingPoints : battingPoints;
            bowlingPoints = (powerType != null && powerType.equals(PowerType.BOWLING)) ? 2 * bowlingPoints : bowlingPoints;
            lockin.setBattingPoints(battingPoints);
            lockin.setBowlingPoints(bowlingPoints);
            lockin.setFieldingPoints(fieldingPoints);
            lockin.setBonusPoints(bonusPoints);
            totalPoints += battingPoints + bowlingPoints + fieldingPoints + bonusPoints;
        }
        return totalPoints;
    }

    public Set<TournamentDto> getAll() {
        Set<Tournament> tournaments = tournamentRepository.findAllByActive(true);
        Set<TournamentDto> dtos = tournaments.stream()
                .map(tournament -> getFromTournament(tournament, false))
                .collect(Collectors.toSet());
        return dtos;
    }

    public TournamentDto get(Integer tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new IllegalArgumentException("no such tournament"));
        return getFromTournament(tournament, true);
    }

    private TournamentDto getFromTournament(Tournament tournament, boolean withMatches) {
        TournamentDto tournamentDto = new TournamentDto();
        BeanUtils.copyProperties(tournament, tournamentDto,"matches");
        if (withMatches) {
            Set<MatchDto> matchDtos = tournament.getMatches().stream()
                    .map(matchService::getFromMatch)
                    .collect(Collectors.toCollection(TreeSet::new));
            tournamentDto.setMatches(matchDtos);
        }
        return tournamentDto;
    }
}
