package com.cricfant.controller;

import com.cricfant.dto.TournamentDto;
import com.cricfant.model.Tournament;
import com.cricfant.repository.TournamentRepository;
import com.cricfant.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @GetMapping
    public ResponseEntity<?> read(){
        Set<TournamentDto> tournamentDtos = tournamentService.getAll();
        return ResponseEntity.ok(tournamentDtos);
    }

    @GetMapping("/{tournamentId}")
    public ResponseEntity<?> read(@PathVariable Integer tournamentId){
        TournamentDto tournamentDto = tournamentService.get(tournamentId);
        return ResponseEntity.ok(tournamentDto);
    }
}
