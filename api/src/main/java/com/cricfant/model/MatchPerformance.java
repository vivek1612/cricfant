package com.cricfant.model;

import com.cricfant.constant.Dismissal;

import javax.persistence.*;

@Entity
@Table(name = "match_performance")
public class MatchPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "team_num", nullable = false)
    private Integer teamNum;

    @Basic
    @Column(name = "batting_points")
    private Integer battingPoints;

    @Basic
    @Column(name = "bowling_points")
    private Integer bowlingPoints;

    @Basic
    @Column(name = "fielding_points")
    private Integer fieldingPoints;

    @Basic
    @Column(name = "bonus_points")
    private Integer bonusPoints;

    @Basic
    @Column(name = "dismissal", nullable = false, length = 50)
    private Dismissal dismissal;

    @Basic
    @Column(name = "mom")
    private Boolean mom;

    @Basic
    @Column(name = "bat_pos")
    private Integer batPos;

    @Basic
    @Column(name = "runs_scored")
    private Integer runsScored;

    @Basic
    @Column(name = "balls_faced")
    private Integer ballsFaced;

    @Basic
    @Column(name = "fours_hit")
    private Integer foursHit;

    @Basic
    @Column(name = "sixes_hit")
    private Integer sixesHit;

    @Basic
    @Column(name = "balls_bowled")
    private Integer ballsBowled;

    @Basic
    @Column(name = "dots_bowled")
    private Integer dotsBowled;

    @Basic
    @Column(name = "maidens_bowled")
    private Integer maidensBowled;

    @Basic
    @Column(name = "runs_given")
    private Integer runsGiven;

    @Basic
    @Column(name = "wickets_taken")
    private Integer wicketsTaken;

    @Basic
    @Column(name = "fours_given")
    private Integer foursGiven;

    @Basic
    @Column(name = "sixes_given")
    private Integer sixesGiven;

    @Basic
    @Column(name = "wides_bowled")
    private Integer widesBowled;

    @Basic
    @Column(name = "no_balls_bowled")
    private Integer noBallsBowled;

    @Basic
    @Column(name = "catches")
    private Integer catches;

    @Basic
    @Column(name = "run_outs")
    private Integer runOuts;

    @Basic
    @Column(name = "stumpings")
    private Integer stumpings;

    @ManyToOne
    @JoinColumn(name = "match_id", referencedColumnName = "id", nullable = false)
    private Match match;

    @ManyToOne
    @JoinColumn(name = "tournament_team_player_id", referencedColumnName = "id", nullable = false)
    private TournamentTeamPlayer tournamentTeamPlayer;

    @Transient
    private String caughtByName;
    @Transient
    private String runOutByName;
    @Transient
    private String stumpedByName;
    @Transient
    private String playerShortName;
    @Transient
    private String dismissedByName;


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

    public Dismissal getDismissal() {
        return dismissal;
    }

    public void setDismissal(Dismissal dismissal) {
        this.dismissal = dismissal;
    }

    public Boolean getMom() {
        return mom;
    }

    public void setMom(Boolean mom) {
        this.mom = mom;
    }

    public Integer getBatPos() {
        return batPos;
    }

    public void setBatPos(Integer batPos) {
        this.batPos = batPos;
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

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match matchByMatchId) {
        this.match = matchByMatchId;
    }

    public TournamentTeamPlayer getTournamentTeamPlayer() {
        return tournamentTeamPlayer;
    }

    public void setTournamentTeamPlayer(TournamentTeamPlayer tournamentTeamPlayerByTournamentTeamPlayerId) {
        this.tournamentTeamPlayer = tournamentTeamPlayerByTournamentTeamPlayerId;
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

    public String getDismissedByName() {
        return dismissedByName;
    }

    public void setDismissedByName(String dismissedByName) {
        this.dismissedByName = dismissedByName;
    }
}
