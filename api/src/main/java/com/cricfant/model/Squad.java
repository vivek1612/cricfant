package com.cricfant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
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
}
