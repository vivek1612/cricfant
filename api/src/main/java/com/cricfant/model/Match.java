package com.cricfant.model;

import com.cricfant.constant.MatchResult;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Timestamp scheduledStart;
    private Integer seqNum;
    private String description;
    @JsonManagedReference("match_matchPerfs")
    @OneToMany(mappedBy = "match")
    private List<MatchPerf> matchPerfs;
    @JsonBackReference("venue_matches")
    @ManyToOne
    @JoinColumn(name = "venue_id", referencedColumnName = "id", nullable = false)
    private Venue venue;
    @JsonBackReference("team_matchesAsTeam1")
    @ManyToOne
    @JoinColumn(name = "team1_id", referencedColumnName = "id", nullable = false)
    private Team team1;
    @JsonBackReference("team_matchesAsTeam2")
    @ManyToOne
    @JoinColumn(name = "team2_id", referencedColumnName = "id", nullable = false)
    private Team team2;
    @JsonBackReference("tournament_matches")
    @ManyToOne
    @JoinColumn(name = "tournament_id", referencedColumnName = "id", nullable = false)
    private Tournament tournament;
    @JsonManagedReference("match_lockins")
    @OneToMany(mappedBy = "match")
    private List<Lockin> lockins;
    private Boolean lockedIn;
    @Enumerated(EnumType.STRING)
    private MatchResult result;
}
