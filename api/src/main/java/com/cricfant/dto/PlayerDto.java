package com.cricfant.dto;

import com.cricfant.constant.PowerType;
import com.cricfant.constant.Skill;
import lombok.Data;

@Data
public class PlayerDto implements Comparable<PlayerDto>{
    private Integer id;
    private String name;
    private String shortName;
    private PowerType powerType;
    private PointsDto points;
    private Skill skill;
    private Integer price;
    private Integer totalPoints;
    private TeamDto team;

    @Override
    public int compareTo(PlayerDto o) {
        return name.compareTo(o.name);
    }
}
