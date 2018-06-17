package com.cricfant.controller;

import com.cricfant.dto.PlayerDto;
import com.cricfant.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping
    public ResponseEntity<?> read(@RequestParam Integer tournamentId) {
        Set<PlayerDto> players = playerService.get(tournamentId);
        return ResponseEntity.ok(players);
    }

}