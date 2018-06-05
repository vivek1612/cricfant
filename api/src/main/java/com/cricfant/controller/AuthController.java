package com.cricfant.controller;

import com.cricfant.dto.UserDto;
import com.cricfant.model.User;
import com.cricfant.repository.UserRepository;
import com.cricfant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;


    @GetMapping("/userProfile")
    @CrossOrigin
    public ResponseEntity<?> getUserProfile(@AuthenticationPrincipal UserDetails userDetails) {
        UserDto dto = userService.getUserProfile(userDetails.getUsername());
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/signUp")
    @CrossOrigin
    public ResponseEntity<?> signUp(@RequestBody UserDto dto) {
        UserDto newUser = userService.signUp(dto);
        return ResponseEntity.ok(newUser);
    }
}