package com.cricfant.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "squad")
public class Squad implements Comparable<Squad> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Basic
    @Column(name = "subs_left", nullable = false)
    private Integer subsLeft;

    @Basic
    @Column(name = "points", nullable = false)
    private Integer points;

    @ManyToMany
    @JoinTable(name = "league_squad",
            joinColumns = @JoinColumn(name = "squad_id"),
            inverseJoinColumns = @JoinColumn(name = "league_id"))
    @OrderBy
    private Set<League> leagues;

    @OneToMany(mappedBy = "squad")
    private Set<Lockin> lockins;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "tournament_id", referencedColumnName = "id", nullable = false)
    private Tournament tournament;

    @OneToMany(mappedBy = "squad", fetch = FetchType.LAZY)
    private Set<SquadPlayer> squadPlayers;

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

    public Integer getSubsLeft() {
        return subsLeft;
    }

    public void setSubsLeft(Integer subsLeft) {
        this.subsLeft = subsLeft;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
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

    public void setLockins(Set<Lockin> lockinsById) {
        this.lockins = lockinsById;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User userByUserId) {
        this.user = userByUserId;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournamentByTournamentId) {
        this.tournament = tournamentByTournamentId;
    }

    public Set<SquadPlayer> getSquadPlayers() {
        return squadPlayers;
    }

    public void setSquadPlayers(Set<SquadPlayer> squadPlayersById) {
        this.squadPlayers = squadPlayersById;
    }

    @Override
    public int compareTo(Squad o) {
        return this.points.compareTo(o.points);
    }
}
