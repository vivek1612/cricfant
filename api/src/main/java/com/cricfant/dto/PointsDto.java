package com.cricfant.dto;

import lombok.Data;

@Data
public class PointsDto {
    private Integer battingPoints;
    private Integer bowlingPoints;
    private Integer fieldingPoints;
    private Integer bonusPoints;
    private Integer totalPoints;
}
