package com.cricfant.service;

import com.cricfant.constant.PowerType;
import com.cricfant.model.*;
import com.cricfant.repository.LockinRepository;
import com.cricfant.repository.MatchPerfRepository;
import com.cricfant.repository.TournamentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class LockinService {

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

    public Match lockin(Integer tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        Optional<Match> oMatchToLockin = matchService.getNextMatch(tournamentId);
        if (!oMatchToLockin.isPresent()) {
            log.warn("no more matches to lockin");
        }
        Match matchToLockin = oMatchToLockin.get();
        log.info("locking in match: " + matchToLockin.getId());
        lockinRepository.deleteAll(matchToLockin.getLockins());
        lockinRepository.flush();
        List<Squad> squads = tournament.getSquads();
        squads.forEach(squad -> {
            Integer subsLeft = squadService.getNonLockedInSubsLeft(squad.getId());
            squad.setSubsLeft(subsLeft);
            Set<SquadPlayer> squadPlayers = squad.getSquadPlayers();
            squadPlayers.forEach(squadPlayer -> {
                Lockin l = new Lockin();
                l.setSquad(squad);
                l.setPlayer(squadPlayer.getPlayer());
                l.setMatch(matchToLockin);
                l.setPowerType(squadPlayer.getPowerType());
                lockinRepository.save(l);
            });
        });
        matchToLockin.setLockedIn(true);
        log.info("locked in match: " + matchToLockin.getId());
        return matchToLockin;
    }

    public void calculatePoints(Integer tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        List<Squad> squads = tournament.getSquads();
        Match lastMatch = matchService.getLastMatch(tournamentId).get();
        squads.forEach(squad -> {
            Set<Lockin> lockins = squad.getLockins().stream()
                    .filter(lockin -> lockin.getMatch().getId().equals(lastMatch.getId()))
                    .collect(Collectors.toSet());
            Integer totalPoints = 0;
            for (Lockin lockin : lockins) {
                MatchPerf mp = matchPerfRepository.findByPlayerIdAndMatchId(
                        lockin.getPlayer().getId(), lockin.getMatch().getId());
                Integer battingPoints = mp.getBattingPoints() == null ? 0 : mp.getBattingPoints();
                Integer bowlingPoints = mp.getBowlingPoints() == null ? 0 : mp.getBowlingPoints();
                Integer fieldingPoints = mp.getFieldingPoints() == null ? 0 : mp.getFieldingPoints();
                Integer bonusPoints = mp.getBonusPoints() == null ? 0 : mp.getBonusPoints();
                PowerType powerType = lockin.getPowerType();
                battingPoints = (powerType != null && powerType.equals(PowerType.BATTING)) ? 2 * battingPoints : battingPoints;
                bowlingPoints = (powerType != null && powerType.equals(PowerType.BOWLING)) ? 2 * bowlingPoints : bowlingPoints;
                lockin.setBattingPoints(battingPoints);
                lockin.setBowlingPoints(bowlingPoints);
                lockin.setFieldingPoints(fieldingPoints);
                lockin.setBonusPoints(bonusPoints);
                totalPoints += battingPoints + bowlingPoints + fieldingPoints + bonusPoints;
            }
            squad.setPoints(squad.getPoints() + totalPoints);
        });
    }
}
