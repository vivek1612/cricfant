package com.cricfant.dto;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class TournamentDto {
    private Integer id;
    private String name;
    private String description;
    private Integer totalSubs;
    private Integer freeSubs;
    private boolean unlimitedSubs;
    private Integer squadBudget;
    private boolean active;
    private Date startDate;
    private Date endDate;
    private Set<MatchDto> matches;
}
