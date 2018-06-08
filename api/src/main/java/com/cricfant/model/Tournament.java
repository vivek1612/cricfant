package com.cricfant.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @JsonManagedReference("tournament_matches")
    @OneToMany(mappedBy = "tournament")
    private List<Match> matches;
    @JsonManagedReference("tournament_leagues")
    @OneToMany(mappedBy = "tournament")
    private List<League> leagues;
    private Integer totalSubs;
    private Integer freeSubs;
    @OneToMany(mappedBy = "tournament")
    private List<Squad> squads;
    @ManyToMany
    @JoinTable(name = "tournament_team",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private List<Team> teams;
    private Boolean unlimitedSubs;

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

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }

    public Integer getTotalSubs() {
        return totalSubs;
    }

    public void setTotalSubs(Integer totalSubs) {
        this.totalSubs = totalSubs;
    }

    public Integer getFreeSubs() {
        return freeSubs;
    }

    public void setFreeSubs(Integer freeSubs) {
        this.freeSubs = freeSubs;
    }

    public List<Squad> getSquads() {
        return squads;
    }

    public void setSquads(List<Squad> squads) {
        this.squads = squads;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Boolean getUnlimitedSubs() {
        return unlimitedSubs;
    }

    public void setUnlimitedSubs(Boolean unlimitedSubs) {
        this.unlimitedSubs = unlimitedSubs;
    }
}
