package com.cricfant.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.SortedSet;

@Data
public class LeagueDto {
    private Integer id;
    private Integer tournamentId;
    private String name;
    private Integer points;
    private List<SquadDto> squads;
}
