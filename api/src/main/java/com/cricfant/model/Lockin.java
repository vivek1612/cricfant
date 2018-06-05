package com.cricfant.model;

import com.cricfant.constant.PowerType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
}
