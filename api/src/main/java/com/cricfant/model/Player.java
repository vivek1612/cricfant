package com.cricfant.model;

import com.cricfant.constant.Skill;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getExtId() {
        return extId;
    }

    public void setExtId(Integer extId) {
        this.extId = extId;
    }

    public List<MatchPerf> getMatchPerfs() {
        return matchPerfs;
    }

    public void setMatchPerfs(List<MatchPerf> matchPerfs) {
        this.matchPerfs = matchPerfs;
    }

    public List<SquadPlayer> getSquadPlayers() {
        return squadPlayers;
    }

    public void setSquadPlayers(List<SquadPlayer> squadPlayers) {
        this.squadPlayers = squadPlayers;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
