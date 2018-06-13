package com.cricfant.model;

import com.cricfant.constant.MatchResult;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "scheduled_start", nullable = false)
    private Timestamp scheduledStart;

    @Basic
    @Column(name = "locked_in", nullable = false)
    private Boolean lockedIn;

    @Basic
    @Column(name = "points_updated", nullable = false)
    private Boolean pointsUpdated;

    @Basic
    @Column(name = "score_updated", nullable = false)
    private Boolean scoreUpdated;

    @Basic
    @Column(name = "seq_num")
    private Integer seqNum;

    @Basic
    @Column(name = "description", length = 256)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "result", length = 50)
    private MatchResult result;

    @OneToMany(mappedBy = "match")
    private Set<Lockin> lockins;

    @ManyToOne
    @JoinColumn(name = "venue_id", referencedColumnName = "id", nullable = false)
    private Venue venue;

    @ManyToOne
    @JoinColumn(name = "team1_id", referencedColumnName = "id", nullable = false)
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_id", referencedColumnName = "id", nullable = false)
    private Team team2;

    @ManyToOne
    @JoinColumn(name = "tournament_id", referencedColumnName = "id", nullable = false)
    private Tournament tournament;

    @OneToMany(mappedBy = "match")
    private Set<MatchPerformance> matchPerformances;

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

    public Boolean getLockedIn() {
        return lockedIn;
    }

    public void setLockedIn(Boolean lockedIn) {
        this.lockedIn = lockedIn;
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

    public MatchResult getResult() {
        return result;
    }

    public void setResult(MatchResult result) {
        this.result = result;
    }

    public Set<Lockin> getLockins() {
        return lockins;
    }

    public void setLockins(Set<Lockin> lockinsById) {
        this.lockins = lockinsById;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venueByVenueId) {
        this.venue = venueByVenueId;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team teamByTeam1Id) {
        this.team1 = teamByTeam1Id;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team teamByTeam2Id) {
        this.team2 = teamByTeam2Id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournamentByTournamentId) {
        this.tournament = tournamentByTournamentId;
    }

    public Set<MatchPerformance> getMatchPerformances() {
        return matchPerformances;
    }

    public void setMatchPerformances(Set<MatchPerformance> matchPerfsById) {
        this.matchPerformances = matchPerfsById;
    }

}
