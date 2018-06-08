package com.cricfant.controller;

import com.cricfant.dto.MatchDto;
import com.cricfant.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/tournaments/{tournamentId}/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping("/{matchId}/performances")
    public ResponseEntity<?> updateScore(@RequestParam String url,
                                         @PathVariable Integer matchId) throws IOException {
        MatchDto matchDto = matchService.processScoresForMatch(url, matchId);
        return ResponseEntity.ok(matchDto);
    }

    @PutMapping("/{matchId}")
    public ResponseEntity<?> setResult(@RequestBody MatchDto matchDto,
                                       @PathVariable Integer matchId) {
        if (matchDto.getId() == null) {
            matchDto.setId(matchId);
        } else if (!matchDto.getId().equals(matchId)) {
            return ResponseEntity.badRequest().body("invalid matchId: " + matchDto.getId());
        }
        MatchDto result = matchService.setResultForMatch(matchDto);
        return ResponseEntity.ok(result);
    }
}