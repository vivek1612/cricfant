package com.cricfant.service;

import com.cricfant.dto.LeagueDto;
import com.cricfant.model.League;
import com.cricfant.model.Squad;
import com.cricfant.model.Tournament;
import com.cricfant.repository.LeagueRepository;
import com.cricfant.repository.SquadRepository;
import com.cricfant.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class LeagueService {

    @Autowired
    TournamentRepository tournamentRepository;
    @Autowired
    LeagueRepository leagueRepository;
    @Autowired
    SquadRepository squadRepository;

    public List<LeagueDto> getLeagues(Integer tournamentId) {
        List<League> leagues = leagueRepository.findAllByTournament_Id(tournamentId);
        List<LeagueDto> leagueDtos = leagues.stream()
                .map(this::getFromLeague).collect(Collectors.toList());
        return leagueDtos;
    }

    public LeagueDto getLeague(Integer leagueId) {
        League league = leagueRepository.findById(leagueId).orElseThrow(() ->
                new IllegalArgumentException("no such league")
        );
        return getFromLeague(league);
    }

    private LeagueDto getFromLeague(League league) {
        LeagueDto l = new LeagueDto();
        l.setId(league.getId());
        l.setName(league.getName());
        l.setTournamentId(league.getTournament().getId());
        Map<Integer, String> squads = new HashMap<>();
        if (league.getSquads() != null) {
            league.getSquads().forEach(squad -> {
                squads.put(squad.getId(), squad.getName());
            });
        }
        l.setSquads(squads);
        return l;
    }

    public LeagueDto createLeague(Integer tournamentId, String name) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        League l = new League();
        l.setName(name);
        l.setTournament(tournament);
        League save = leagueRepository.save(l);
        return getFromLeague(save);
    }

    public void join(Integer leagueId, Integer squadId, Integer userId) {
        Optional<League> oLeague = leagueRepository.findById(leagueId);
        if (!oLeague.isPresent()) {
            throw new IllegalArgumentException("no such league");
        }

        Optional<Squad> oSquad = squadRepository.findById(squadId);
        if (!oSquad.isPresent()) {
            throw new IllegalArgumentException("no such squad");
        }
        Squad squad = oSquad.get();
        if (!squad.getUser().getId().equals(userId)) {
            throw new IllegalStateException("user does not own squad");
        }
        boolean joined = squad.getLeagues().add(oLeague.get());
        if (!joined) {
            throw new IllegalStateException("squad " + squadId + " already belongs to league " + leagueId);
        }
    }
}
