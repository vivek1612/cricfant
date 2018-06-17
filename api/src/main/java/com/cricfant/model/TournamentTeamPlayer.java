package com.cricfant.model;

import com.cricfant.constant.Skill;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tournament_team_player")
public class TournamentTeamPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "skill", nullable = false, length = 50)
    private Skill skill;

    @Basic
    @Column(name = "price", nullable = false)
    private Integer price;

    @Basic
    @Column(name = "points", nullable = false)
    private Integer points;

    @OneToMany(mappedBy = "tournamentTeamPlayer")
    private Set<Lockin> lockins;

    @OneToMany(mappedBy = "tournamentTeamPlayer")
    private Set<MatchPerformance> matchPerformances;

    @OneToMany(mappedBy = "tournamentTeamPlayer")
    private Set<SquadPlayer> squadPlayers;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id", referencedColumnName = "id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "tournament_team_id", referencedColumnName = "id", nullable = false)
    private TournamentTeam tournamentTeam;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<Lockin> getLockins() {
        return lockins;
    }

    public void setLockins(Set<Lockin> lockinsById) {
        this.lockins = lockinsById;
    }

    public Set<MatchPerformance> getMatchPerformances() {
        return matchPerformances;
    }

    public void setMatchPerformances(Set<MatchPerformance> matchPerfsById) {
        this.matchPerformances = matchPerfsById;
    }

    public Set<SquadPlayer> getSquadPlayers() {
        return squadPlayers;
    }

    public void setSquadPlayers(Set<SquadPlayer> squadPlayersById) {
        this.squadPlayers = squadPlayersById;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player playerByPlayerId) {
        this.player = playerByPlayerId;
    }

    public TournamentTeam getTournamentTeam() {
        return tournamentTeam;
    }

    public void setTournamentTeam(TournamentTeam tournamentTeamByTournamentTeamId) {
        this.tournamentTeam = tournamentTeamByTournamentTeamId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
