package com.cricfant.controller;

import com.cricfant.dto.CustomPrincipal;
import com.cricfant.dto.LeagueDto;
import com.cricfant.model.User;
import com.cricfant.repository.UserRepository;
import com.cricfant.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leagues")
public class LeagueController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LeagueService leagueService;


    @GetMapping
    public ResponseEntity<?> readLeagues(@RequestParam Integer tournamentId) {
        List<LeagueDto> leagues = leagueService.getLeagues(tournamentId);
        return ResponseEntity.ok(leagues);
    }

    @GetMapping("/{leagueId}")
    public ResponseEntity<?> readLeague(@PathVariable Integer leagueId) {
        LeagueDto league = leagueService.getLeague(leagueId);
        return ResponseEntity.ok(league);
    }

    @PostMapping
    public ResponseEntity<?> createLeague(@PathVariable Integer tournamentId,
                                          @RequestParam String name) {
        LeagueDto league = leagueService.createLeague(tournamentId, name);
        return ResponseEntity.ok(league);
    }

    @PutMapping("/{leagueId}/squads/{squadId}")
    public ResponseEntity<?> joinLeague(@PathVariable Integer leagueId,
                                        @PathVariable Integer squadId,
                                        @AuthenticationPrincipal CustomPrincipal principal) {
        User user = userRepository.findById(principal.getId()).get();
        leagueService.join(leagueId, squadId, user.getId());
        return ResponseEntity.ok("joined league");
    }
}