package com.cricfant.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String shortName;
    @JsonManagedReference("team_matchesAsTeam1")
    @OneToMany(mappedBy = "team1")
    private List<Match> matchesAsTeam1;
    @JsonManagedReference("team_matchesAsTeam1")
    @OneToMany(mappedBy = "team2")
    private List<Match> matchesAsTeam2;
    @JsonManagedReference("team_players")
    @OneToMany(mappedBy = "team")
    private List<Player> players;
    @ManyToMany
    @JoinTable(name = "tournament_team",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "tournament_id"))
    private List<Tournament> tournaments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<Match> getMatchesAsTeam1() {
        return matchesAsTeam1;
    }

    public void setMatchesAsTeam1(List<Match> matchesAsTeam1) {
        this.matchesAsTeam1 = matchesAsTeam1;
    }

    public List<Match> getMatchesAsTeam2() {
        return matchesAsTeam2;
    }

    public void setMatchesAsTeam2(List<Match> matchesAsTeam2) {
        this.matchesAsTeam2 = matchesAsTeam2;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }
}
