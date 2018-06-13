package com.cricfant.dto;

import lombok.Data;

import java.util.List;

@Data
public class SquadDto {
    private Integer id;
    private Integer tournamentId;
    private String name;
    private Integer forMatchId;
    private Integer subsLeft;
    private Integer points;
    private Integer lastMatchPoints;
    private List<LeagueForSquadDto> leagues;
    private List<PlayerDto> players;
}
