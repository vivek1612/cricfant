package com.cricfant.model;

import com.cricfant.constant.PowerType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "squad_player")
public class SquadPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonBackReference("squad_squadPlayers")
    @ManyToOne
    @JoinColumn(name = "squad_id")
    private Squad squad;
    @JsonBackReference("player_squadPlayers")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    private Player player;
    @Enumerated(EnumType.STRING)
    private PowerType powerType;
}
