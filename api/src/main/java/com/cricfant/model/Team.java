package com.cricfant.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Basic
    @Column(name = "short_name", nullable = false, length = 8)
    private String shortName;

    @Basic
    @Column(name = "ext_id")
    private Integer extId;

    @OneToMany(mappedBy = "team1")
    private Set<Match> matchesByTeam1;

    @OneToMany(mappedBy = "team2")
    private Set<Match> matchesByTeam2;

    @OneToMany(mappedBy = "team")
    private Set<TournamentTeam> tournamentTeams;

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

    public Integer getExtId() {
        return extId;
    }

    public void setExtId(Integer extId) {
        this.extId = extId;
    }

    public Set<Match> getMatchesByTeam1() {
        return matchesByTeam1;
    }

    public void setMatchesByTeam1(Set<Match> matchesById) {
        this.matchesByTeam1 = matchesById;
    }

    public Set<Match> getMatchesByTeam2() {
        return matchesByTeam2;
    }

    public void setMatchesByTeam2(Set<Match> matchesById_0) {
        this.matchesByTeam2 = matchesById_0;
    }

    public Set<TournamentTeam> getTournamentTeams() {
        return tournamentTeams;
    }

    public void setTournamentTeams(Set<TournamentTeam> tournamentTeamsById) {
        this.tournamentTeams = tournamentTeamsById;
    }
}
