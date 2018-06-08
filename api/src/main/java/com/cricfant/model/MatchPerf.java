package com.cricfant.model;

import com.cricfant.constant.Dismissal;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "match_perf")
public class MatchPerf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer teamNum;
    private Integer batPos;
    @Enumerated(EnumType.STRING)
    private Dismissal dismissal;
    private Integer runsScored;
    private Integer ballsFaced;
    private Integer foursHit;
    private Integer sixesHit;
    private Integer ballsBowled;
    private Integer dotsBowled;
    private Integer maidensBowled;
    private Integer runsGiven;
    private Integer wicketsTaken;
    private Integer foursGiven;
    private Integer sixesGiven;
    private Integer widesBowled;
    private Integer noBallsBowled;
    @Transient
    private String dismissedByName;
    @Transient
    private String caughtByName;
    @Transient
    private String runOutByName;
    @Transient
    private String stumpedByName;
    @Transient
    private String playerShortName;
    @JsonBackReference("player_matchPerfs")
    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id", nullable = false)
    private Player player;
    @JsonBackReference("match_matchPerfs")
    @ManyToOne
    @JoinColumn(name = "match_id", referencedColumnName = "id", nullable = false)
    private Match match;
    private Boolean mom;
    private Integer battingPoints;
    private Integer bowlingPoints;
    private Integer fieldingPoints;
    private Integer bonusPoints;
    private Integer catches;
    private Integer runOuts;
    private Integer stumpings;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(Integer teamNum) {
        this.teamNum = teamNum;
    }

    public Integer getBatPos() {
        return batPos;
    }

    public void setBatPos(Integer batPos) {
        this.batPos = batPos;
    }

    public Dismissal getDismissal() {
        return dismissal;
    }

    public void setDismissal(Dismissal dismissal) {
        this.dismissal = dismissal;
    }

    public Integer getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(Integer runsScored) {
        this.runsScored = runsScored;
    }

    public Integer getBallsFaced() {
        return ballsFaced;
    }

    public void setBallsFaced(Integer ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public Integer getFoursHit() {
        return foursHit;
    }

    public void setFoursHit(Integer foursHit) {
        this.foursHit = foursHit;
    }

    public Integer getSixesHit() {
        return sixesHit;
    }

    public void setSixesHit(Integer sixesHit) {
        this.sixesHit = sixesHit;
    }

    public Integer getBallsBowled() {
        return ballsBowled;
    }

    public void setBallsBowled(Integer ballsBowled) {
        this.ballsBowled = ballsBowled;
    }

    public Integer getDotsBowled() {
        return dotsBowled;
    }

    public void setDotsBowled(Integer dotsBowled) {
        this.dotsBowled = dotsBowled;
    }

    public Integer getMaidensBowled() {
        return maidensBowled;
    }

    public void setMaidensBowled(Integer maidensBowled) {
        this.maidensBowled = maidensBowled;
    }

    public Integer getRunsGiven() {
        return runsGiven;
    }

    public void setRunsGiven(Integer runsGiven) {
        this.runsGiven = runsGiven;
    }

    public Integer getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(Integer wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    public Integer getFoursGiven() {
        return foursGiven;
    }

    public void setFoursGiven(Integer foursGiven) {
        this.foursGiven = foursGiven;
    }

    public Integer getSixesGiven() {
        return sixesGiven;
    }

    public void setSixesGiven(Integer sixesGiven) {
        this.sixesGiven = sixesGiven;
    }

    public Integer getWidesBowled() {
        return widesBowled;
    }

    public void setWidesBowled(Integer widesBowled) {
        this.widesBowled = widesBowled;
    }

    public Integer getNoBallsBowled() {
        return noBallsBowled;
    }

    public void setNoBallsBowled(Integer noBallsBowled) {
        this.noBallsBowled = noBallsBowled;
    }

    public String getDismissedByName() {
        return dismissedByName;
    }

    public void setDismissedByName(String dismissedByName) {
        this.dismissedByName = dismissedByName;
    }

    public String getCaughtByName() {
        return caughtByName;
    }

    public void setCaughtByName(String caughtByName) {
        this.caughtByName = caughtByName;
    }

    public String getRunOutByName() {
        return runOutByName;
    }

    public void setRunOutByName(String runOutByName) {
        this.runOutByName = runOutByName;
    }

    public String getStumpedByName() {
        return stumpedByName;
    }

    public void setStumpedByName(String stumpedByName) {
        this.stumpedByName = stumpedByName;
    }

    public String getPlayerShortName() {
        return playerShortName;
    }

    public void setPlayerShortName(String playerShortName) {
        this.playerShortName = playerShortName;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Boolean getMom() {
        return mom;
    }

    public void setMom(Boolean mom) {
        this.mom = mom;
    }

    public Integer getBattingPoints() {
        return battingPoints;
    }

    public void setBattingPoints(Integer battingPoints) {
        this.battingPoints = battingPoints;
    }

    public Integer getBowlingPoints() {
        return bowlingPoints;
    }

    public void setBowlingPoints(Integer bowlingPoints) {
        this.bowlingPoints = bowlingPoints;
    }

    public Integer getFieldingPoints() {
        return fieldingPoints;
    }

    public void setFieldingPoints(Integer fieldingPoints) {
        this.fieldingPoints = fieldingPoints;
    }

    public Integer getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(Integer bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public Integer getCatches() {
        return catches;
    }

    public void setCatches(Integer catches) {
        this.catches = catches;
    }

    public Integer getRunOuts() {
        return runOuts;
    }

    public void setRunOuts(Integer runOuts) {
        this.runOuts = runOuts;
    }

    public Integer getStumpings() {
        return stumpings;
    }

    public void setStumpings(Integer stumpings) {
        this.stumpings = stumpings;
    }
}
