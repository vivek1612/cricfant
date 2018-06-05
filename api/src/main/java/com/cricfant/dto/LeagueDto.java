package com.cricfant.dto;

import lombok.Data;

import java.util.Map;

@Data
public class LeagueDto {
    private Integer id;
    private Integer tournamentId;
    private String name;
    private Map<Integer,String> squads;
}
