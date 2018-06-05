package com.cricfant.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String shortName;
    @JsonManagedReference("team_matchesAsTeam1")
    @OneToMany(mappedBy = "team1")
    private List<Match> matchesAsTeam1;
    @JsonManagedReference("team_matchesAsTeam1")
    @OneToMany(mappedBy = "team2")
    private List<Match> matchesAsTeam2;
    @JsonManagedReference("team_players")
    @OneToMany(mappedBy = "team")
    private List<Player> players;
    @ManyToMany
    @JoinTable(name = "tournament_team",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "tournament_id"))
    private List<Tournament> tournaments;
}
