package com.cricfant.dto;

import com.cricfant.constant.Role;
import lombok.Data;

import java.util.Map;

@Data
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private Role role;
}
