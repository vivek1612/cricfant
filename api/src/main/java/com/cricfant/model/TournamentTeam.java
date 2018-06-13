package com.cricfant.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tournament_team")
public class TournamentTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "tournament_id", referencedColumnName = "id", nullable = false)
    private Tournament tournament;

    @OneToMany(mappedBy = "tournamentTeam")
    private Set<TournamentTeamPlayer> tournamentTeamPlayers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team teamByTeamId) {
        this.team = teamByTeamId;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournamentByTournamentId) {
        this.tournament = tournamentByTournamentId;
    }

    public Set<TournamentTeamPlayer> getTournamentTeamPlayers() {
        return tournamentTeamPlayers;
    }

    public void setTournamentTeamPlayers(Set<TournamentTeamPlayer> tournamentTeamPlayersById) {
        this.tournamentTeamPlayers = tournamentTeamPlayersById;
    }
}
