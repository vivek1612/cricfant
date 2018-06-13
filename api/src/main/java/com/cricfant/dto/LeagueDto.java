package com.cricfant.dto;

import com.cricfant.constant.LeagueType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(Include.NON_NULL)
public class LeagueDto {
    private Integer id;
    private Integer tournamentId;
    private String name;
    private Integer points;
    private LeagueType type;
    private List<SquadDto> squads;
    private String adminName;
}
