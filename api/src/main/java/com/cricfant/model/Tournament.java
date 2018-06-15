package com.cricfant.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tournament")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Basic
    @Column(name = "description", length = 120)
    private String description;

    @Basic
    @Column(name = "total_subs", nullable = false)
    private Integer totalSubs;

    @Basic
    @Column(name = "free_subs", nullable = false)
    private Integer freeSubs;

    @Basic
    @Column(name = "unlimited_subs", nullable = false)
    private Boolean unlimitedSubs;

    @Basic
    @Column(name = "start_date")
    private Date startDate;

    @Basic
    @Column(name = "end_date")
    private Date endDate;

    @Basic
    @Column(name = "active", nullable = false)
    private boolean active;

    @Basic
    @Column(name = "squad_budget", nullable = false)
    private Integer squadBudget;

    @OneToMany(mappedBy = "tournament")
    private Set<League> leagues;

    @OneToMany(mappedBy = "tournament")
    private Set<Match> matches;

    @OneToMany(mappedBy = "tournament")
    private Set<Squad> squads;

    @OneToMany(mappedBy = "tournament")
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

    public Boolean getUnlimitedSubs() {
        return unlimitedSubs;
    }

    public void setUnlimitedSubs(Boolean unlimitedSubs) {
        this.unlimitedSubs = unlimitedSubs;
    }

    public Integer getSquadBudget() {
        return squadBudget;
    }

    public void setSquadBudget(Integer squadBudget) {
        this.squadBudget = squadBudget;
    }

    public Set<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(Set<League> leaguesById) {
        this.leagues = leaguesById;
    }

    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matchesById) {
        this.matches = matchesById;
    }

    public Set<Squad> getSquads() {
        return squads;
    }

    public void setSquads(Set<Squad> squadsById) {
        this.squads = squadsById;
    }

    public Set<TournamentTeam> getTournamentTeams() {
        return tournamentTeams;
    }

    public void setTournamentTeams(Set<TournamentTeam> tournamentTeamsById) {
        this.tournamentTeams = tournamentTeamsById;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
