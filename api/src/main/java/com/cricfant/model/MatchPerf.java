package com.cricfant.model;

import com.cricfant.constant.Dismissal;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
}
