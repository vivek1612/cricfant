package com.cricfant.controller;

import com.cricfant.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @Secured("ROLE_ADMIN")
    @PutMapping("/{tournamentId}/lockin")
    public ResponseEntity<?> lockin(@PathVariable Integer tournamentId) {

        tournamentService.lockin(tournamentId);
        return ResponseEntity.ok("");
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{tournamentId}/calculate-points")
    public ResponseEntity<?> calculatePoints(@PathVariable Integer tournamentId){
        tournamentService.calculatePoints(tournamentId);
        return ResponseEntity.ok("points calculated");
    }
}
