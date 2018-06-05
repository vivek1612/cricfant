package com.cricfant.model;

import com.cricfant.constant.Skill;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer extId;
    @JsonManagedReference("player_matchPerfs")
    @OneToMany(mappedBy = "player")
    private List<MatchPerf> matchPerfs;
    @JsonBackReference("player_squadPlayers")
    @OneToMany(mappedBy = "player")
    private List<SquadPlayer> squadPlayers;
    @JsonBackReference("team_players")
    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
    private Team team;
    @Enumerated(EnumType.STRING)
    private Skill skill;
}
