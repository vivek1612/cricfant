package com.cricfant.dto;

import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
public class SquadDto {
    private Integer id;
    private Integer tournamentId;
    private String name;
    private Integer forMatchId;
    private Integer subsLeft;
    private Map<Integer, String> leagues;
    private Set<PlayerDto> players;
}
