package com.cricfant.controller;

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
@RequestMapping("/league")
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
    public ResponseEntity<?> createLeague(@RequestParam Integer tournamentId,
                                          @RequestParam String name) {
        LeagueDto league = leagueService.createLeague(tournamentId, name);
        return ResponseEntity.ok(league);
    }

    @PostMapping("/{leagueId}/join")
    public ResponseEntity<?> joinLeague(@PathVariable Integer leagueId,
                                        @RequestParam Integer squadId,
                                        @AuthenticationPrincipal User user) {
//        user = userRepository.getOne(user.getId());
        user = userRepository.findByEmail("vivek.1612@gmail.com");
        leagueService.join(leagueId, squadId, user.getId());
        return ResponseEntity.ok("joined league");
    }
}