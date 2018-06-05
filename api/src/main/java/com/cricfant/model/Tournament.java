package com.cricfant.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @JsonManagedReference("tournament_matches")
    @OneToMany(mappedBy = "tournament")
    private List<Match> matches;
    @JsonManagedReference("tournament_leagues")
    @OneToMany(mappedBy = "tournament")
    private List<League> leagues;
    private Integer totalSubs;
    private Integer freeSubs;
    @OneToMany(mappedBy = "tournament")
    private List<Squad> squads;
    @ManyToMany
    @JoinTable(name = "tournament_team",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private List<Team> teams;
    private Boolean unlimitedSubs;
}
