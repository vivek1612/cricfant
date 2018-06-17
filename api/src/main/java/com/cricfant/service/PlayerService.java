package com.cricfant.service;

import com.cricfant.dto.PlayerDto;
import com.cricfant.dto.TeamDto;
import com.cricfant.model.Player;
import com.cricfant.model.Team;
import com.cricfant.model.TournamentTeamPlayer;
import com.cricfant.repository.TournamentTeamPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    TournamentTeamPlayerRepository tournamentTeamPlayerRepository;

    public Set<PlayerDto> get(Integer tournamentId) {
        Set<TournamentTeamPlayer> players = tournamentTeamPlayerRepository.findAllByTournamentTeam_TournamentId(tournamentId);
        return players.stream()
                .map(tournamentTeamPlayer -> getFromTTP(tournamentTeamPlayer))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private PlayerDto getFromTTP(TournamentTeamPlayer tournamentTeamPlayer) {
        PlayerDto playerDto = new PlayerDto();
        playerDto.setSkill(tournamentTeamPlayer.getSkill());
        playerDto.setPrice(tournamentTeamPlayer.getPrice());
        playerDto.setId(tournamentTeamPlayer.getId());
        Player player = tournamentTeamPlayer.getPlayer();
        playerDto.setTotalPoints(tournamentTeamPlayer.getPoints());
        playerDto.setName(player.getName());
        TeamDto teamDto = new TeamDto();
        Team team = tournamentTeamPlayer.getTournamentTeam().getTeam();
        teamDto.setId(team.getId());
        teamDto.setName(team.getName());
        teamDto.setShortName(team.getShortName());
        playerDto.setTeam(teamDto);
        return playerDto;
    }
}
