package com.cricfant.model;

import com.cricfant.constant.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uid;
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    @JsonManagedReference("user_squads")
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Squad> squads;
}
