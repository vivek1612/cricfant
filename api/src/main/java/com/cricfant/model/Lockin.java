package com.cricfant.model;

import com.cricfant.constant.PowerType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
public class Lockin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer battingPoints;
    private Integer bowlingPoints;
    private Integer fieldingPoints;
    private Integer bonusPoints;
    @JsonBackReference("squad_lockins")
    @ManyToOne
    @JoinColumn(name = "squad_id", referencedColumnName = "id", nullable = false)
    private Squad squad;
    @JsonBackReference("player_lockins")
    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id", nullable = false)
    private Player player;
    @JsonBackReference("match_lockins")
    @ManyToOne
    @JoinColumn(name = "match_id", referencedColumnName = "id", nullable = false)
    private Match match;
    @JsonBackReference("playerPowerType_lockins")
    @Enumerated(EnumType.STRING)
    private PowerType powerType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Squad getSquad() {
        return squad;
    }

    public void setSquad(Squad squad) {
        this.squad = squad;
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

    public PowerType getPowerType() {
        return powerType;
    }

    public void setPowerType(PowerType powerType) {
        this.powerType = powerType;
    }
}
