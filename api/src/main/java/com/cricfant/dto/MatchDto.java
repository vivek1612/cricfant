package com.cricfant.dto;

import com.cricfant.constant.MatchResult;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class MatchDto {
    private Integer id;
    private Integer tournamentId;
    private Timestamp scheduledStart;
    private Integer seqNum;
    private String description;
    private String venue;
    private List<TeamDto> teams;
    private boolean lockedIn;
    private MatchResult result;
    private List<PlayerDto> performances;
}
