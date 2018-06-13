package com.cricfant.model;

import com.cricfant.constant.PowerType;

import javax.persistence.*;

@Entity
@Table(name = "lockin")
public class Lockin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "power_type", length = 32)
    private PowerType powerType;

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

    @ManyToOne
    @JoinColumn(name = "match_id", referencedColumnName = "id", nullable = false)
    private Match match;

    @ManyToOne
    @JoinColumn(name = "squad_id", referencedColumnName = "id", nullable = false)
    private Squad squad;

    @ManyToOne
    @JoinColumn(name = "tournament_team_player_id", referencedColumnName = "id", nullable = false)
    private TournamentTeamPlayer tournamentTeamPlayer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PowerType getPowerType() {
        return powerType;
    }

    public void setPowerType(PowerType powerType) {
        this.powerType = powerType;
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

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match matchByMatchId) {
        this.match = matchByMatchId;
    }

    public Squad getSquad() {
        return squad;
    }

    public void setSquad(Squad squadBySquadId) {
        this.squad = squadBySquadId;
    }

    public TournamentTeamPlayer getTournamentTeamPlayer() {
        return tournamentTeamPlayer;
    }

    public void setTournamentTeamPlayer(TournamentTeamPlayer tournamentTeamPlayerByTournamentTeamPlayerId) {
        this.tournamentTeamPlayer = tournamentTeamPlayerByTournamentTeamPlayerId;
    }
}
