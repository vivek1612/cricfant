package com.cricfant.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "name", nullable = false, length = 256)
    private String name;

    @Basic
    @Column(name = "ext_id")
    private Integer extId;

    @OneToMany(mappedBy = "player")
    private Set<TournamentTeamPlayer> tournamentTeamPlayers;

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

    public Set<TournamentTeamPlayer> getTournamentTeamPlayers() {
        return tournamentTeamPlayers;
    }

    public void setTournamentTeamPlayers(Set<TournamentTeamPlayer> tournamentTeamPlayersById) {
        this.tournamentTeamPlayers = tournamentTeamPlayersById;
    }
}
