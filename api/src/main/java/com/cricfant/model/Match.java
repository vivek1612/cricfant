package com.cricfant.model;

import com.cricfant.constant.MatchResult;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Timestamp scheduledStart;
    private Integer seqNum;
    private String description;
    @JsonManagedReference("match_matchPerfs")
    @OneToMany(mappedBy = "match")
    private List<MatchPerf> matchPerfs;
    @JsonBackReference("venue_matches")
    @ManyToOne
    @JoinColumn(name = "venue_id", referencedColumnName = "id", nullable = false)
    private Venue venue;
    @JsonBackReference("team_matchesAsTeam1")
    @ManyToOne
    @JoinColumn(name = "team1_id", referencedColumnName = "id", nullable = false)
    private Team team1;
    @JsonBackReference("team_matchesAsTeam2")
    @ManyToOne
    @JoinColumn(name = "team2_id", referencedColumnName = "id", nullable = false)
    private Team team2;
    @JsonBackReference("tournament_matches")
    @ManyToOne
    @JoinColumn(name = "tournament_id", referencedColumnName = "id", nullable = false)
    private Tournament tournament;
    @JsonManagedReference("match_lockins")
    @OneToMany(mappedBy = "match")
    private List<Lockin> lockins;
    private Boolean lockedIn;
    private Boolean pointsUpdated;
    private Boolean scoreUpdated;
    @Enumerated(EnumType.STRING)
    private MatchResult result;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getScheduledStart() {
        return scheduledStart;
    }

    public void setScheduledStart(Timestamp scheduledStart) {
        this.scheduledStart = scheduledStart;
    }

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MatchPerf> getMatchPerfs() {
        return matchPerfs;
    }

    public void setMatchPerfs(List<MatchPerf> matchPerfs) {
        this.matchPerfs = matchPerfs;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public List<Lockin> getLockins() {
        return lockins;
    }

    public void setLockins(List<Lockin> lockins) {
        this.lockins = lockins;
    }

    public Boolean getLockedIn() {
        return lockedIn;
    }

    public void setLockedIn(Boolean lockedIn) {
        this.lockedIn = lockedIn;
    }

    public MatchResult getResult() {
        return result;
    }

    public void setResult(MatchResult result) {
        this.result = result;
    }

    public Boolean getPointsUpdated() {
        return pointsUpdated;
    }

    public void setPointsUpdated(Boolean pointsUpdated) {
        this.pointsUpdated = pointsUpdated;
    }

    public Boolean getScoreUpdated() {
        return scoreUpdated;
    }

    public void setScoreUpdated(Boolean scoreUpdated) {
        this.scoreUpdated = scoreUpdated;
    }
}
