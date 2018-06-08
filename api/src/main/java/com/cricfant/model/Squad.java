package com.cricfant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Squad{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @JsonBackReference("user_squads")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Integer subsLeft;
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
    @JsonManagedReference("squad_squadPlayers")
    @OneToMany(mappedBy = "squad", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Set<SquadPlayer> squadPlayers;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "league_squad",
            joinColumns = @JoinColumn(name = "squad_id"),
            inverseJoinColumns = @JoinColumn(name = "league_id"))
    private Set<League> leagues;
    @JsonManagedReference("squad_lockins")
    @OneToMany(mappedBy = "squad")
    @EqualsAndHashCode.Exclude
    private Set<Lockin> lockins;
    private Integer points;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getSubsLeft() {
        return subsLeft;
    }

    public void setSubsLeft(Integer subsLeft) {
        this.subsLeft = subsLeft;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Set<SquadPlayer> getSquadPlayers() {
        return squadPlayers;
    }

    public void setSquadPlayers(Set<SquadPlayer> squadPlayers) {
        this.squadPlayers = squadPlayers;
    }

    public Set<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(Set<League> leagues) {
        this.leagues = leagues;
    }

    public Set<Lockin> getLockins() {
        return lockins;
    }

    public void setLockins(Set<Lockin> lockins) {
        this.lockins = lockins;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
