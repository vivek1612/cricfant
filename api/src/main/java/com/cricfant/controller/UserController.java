package com.cricfant.controller;

import com.cricfant.dto.UserDto;
import com.cricfant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("principal.id == #userId")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserProfile(@PathVariable Integer userId) {
        UserDto dto = userService.getUserProfile(userId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping()
    public ResponseEntity<?> register(@RequestBody UserDto dto) {
        UserDto newUser = userService.register(dto);
        return ResponseEntity.ok(newUser);
    }
}