package com.cricfant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@Entity
@Data
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @JsonBackReference("tournament_leagues")
    @ManyToOne
    @JoinColumn(name = "tournament_id", referencedColumnName = "id", nullable = false)
    private Tournament tournament;
    @ManyToMany
    @JoinTable(name = "league_squad",
            joinColumns = @JoinColumn(name = "league_id"),
            inverseJoinColumns = @JoinColumn(name = "squad_id"))
    private List<Squad> squads;
}
