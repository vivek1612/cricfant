package com.cricfant.controller;

import com.cricfant.model.Match;
import com.cricfant.service.LockinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{tournamentId}")
public class LockinController {

    @Autowired
    private LockinService lockinService;

    @GetMapping("/lockin")
    public ResponseEntity<?> lockin(@PathVariable Integer tournamentId) {

        Match lockedInMatch = lockinService.lockin(tournamentId);
        return ResponseEntity.ok(lockedInMatch);
    }

    @GetMapping("/calculatePoints")
    public ResponseEntity<?> calculatePoints(@PathVariable Integer tournamentId){
        lockinService.calculatePoints(tournamentId);
        return ResponseEntity.ok("points calculated");
    }
}
