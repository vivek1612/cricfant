package com.cricfant.service;

import com.cricfant.constant.LeagueType;
import com.cricfant.dto.LeagueDto;
import com.cricfant.dto.SquadDto;
import com.cricfant.model.League;
import com.cricfant.model.Squad;
import com.cricfant.model.Tournament;
import com.cricfant.model.User;
import com.cricfant.repository.LeagueRepository;
import com.cricfant.repository.SquadRepository;
import com.cricfant.repository.TournamentRepository;
import com.cricfant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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
    @Autowired
    SquadService squadService;
    @Autowired
    UserRepository userRepository;

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
        l.setPoints(league.getPoints());
        l.setType(league.getType());
        league.getAdmin().ifPresent(user -> l.setAdminName(user.getName()));
        List<SquadDto> squads = new ArrayList<>();
        if (league.getSquads() != null) {
            league.getSquads().forEach(squad -> {
                squads.add(squadService.getFromSquad(squad));
            });
        }
        l.setSquads(squads);
        return l;
    }

    public LeagueDto createLeague(LeagueDto leagueDto, Integer userId) {
        Tournament tournament = tournamentRepository
                .findById(leagueDto.getTournamentId()).orElseThrow(() ->
                        new IllegalArgumentException("no such tournament"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("no such user"));
        League l = new League();
        l.setName(leagueDto.getName());
        l.setTournament(tournament);
        l.setPoints(0);
        l.setType(leagueDto.getType() == null ? LeagueType.PUBLIC : leagueDto.getType());
        if (l.getType() == LeagueType.PRIVATE) l.setAdmin(user);
        League save = leagueRepository.save(l);
        return getFromLeague(save);
    }

    public void join(Integer leagueId, Integer squadId, Integer userId) {
        League league = leagueRepository.findById(leagueId).orElseThrow(() ->
                new IllegalArgumentException("no such league"));
        Squad squad = squadRepository.findById(squadId).orElseThrow(() ->
                new IllegalArgumentException("no such squad"));
        if (!squad.getUser().getId().equals(userId)) {
            throw new IllegalStateException("user does not own squad");
        }
        boolean joined = squad.getLeagues().add(league);
        if (!joined) {
            throw new IllegalStateException("squad " + squadId + " already belongs to league " + leagueId);
        }
    }
}
