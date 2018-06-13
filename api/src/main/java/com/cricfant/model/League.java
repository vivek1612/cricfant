package com.cricfant.model;

import com.cricfant.constant.LeagueType;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.Optional;
import java.util.SortedSet;

@Entity
@Table(name = "league")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Basic
    @Column(name = "points", nullable = false)
    private Integer points;

    @ManyToOne
    @JoinColumn(name = "tournament_id", referencedColumnName = "id", nullable = false)
    private Tournament tournament;

    @ManyToMany
    @JoinTable(name = "league_squad",
            joinColumns = @JoinColumn(name = "league_id"),
            inverseJoinColumns = @JoinColumn(name = "squad_id"))
    @OrderBy("points desc")
    @SortNatural
    private SortedSet<Squad> squads;

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private User admin;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 50, nullable = false)
    private LeagueType type;

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

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournamentByTournamentId) {
        this.tournament = tournamentByTournamentId;
    }

    public SortedSet<Squad> getSquads() {
        return squads;
    }

    public void setSquads(SortedSet<Squad> squads) {
        this.squads = squads;
    }

    public Optional<User> getAdmin() {
        return Optional.ofNullable(admin);
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public LeagueType getType() {
        return type;
    }

    public void setType(LeagueType type) {
        this.type = type;
    }
}
