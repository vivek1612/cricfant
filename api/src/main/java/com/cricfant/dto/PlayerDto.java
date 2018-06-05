package com.cricfant.dto;

import com.cricfant.constant.PowerType;
import com.cricfant.constant.Skill;
import lombok.Data;

@Data
public class PlayerDto {
    private Integer id;
    private String name;
    private String shortName;
    private PowerType powerType;
    private PointsDto points;
    private Skill skill;
}
