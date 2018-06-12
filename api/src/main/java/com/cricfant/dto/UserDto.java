package com.cricfant.dto;

import com.cricfant.constant.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Role role;
}
