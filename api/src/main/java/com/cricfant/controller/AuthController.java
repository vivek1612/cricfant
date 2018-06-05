package com.cricfant.controller;

import com.cricfant.model.User;
import com.cricfant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/user")
    @CrossOrigin
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUid(
                userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("no such user"));
        return ResponseEntity.ok(user);
    }

}