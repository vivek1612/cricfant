package com.cricfant.controller;

import com.cricfant.dto.CustomPrincipal;
import com.cricfant.dto.SquadDto;
import com.cricfant.model.Squad;
import com.cricfant.model.User;
import com.cricfant.repository.SquadRepository;
import com.cricfant.repository.UserRepository;
import com.cricfant.service.MatchService;
import com.cricfant.service.SquadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/squads")
public class SquadController {

    @Autowired
    SquadService squadService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SquadRepository squadRepository;
    @Autowired
    MatchService matchService;

    @PostMapping
    public ResponseEntity<?> create(@AuthenticationPrincipal CustomPrincipal principal,
                                    @RequestBody SquadDto squadDto) {
        User user = userRepository.findById(principal.getId()).get();
        SquadDto dto = squadService.createSquad(user.getId(), squadDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<?> read(@AuthenticationPrincipal CustomPrincipal principal) {
        List<SquadDto> squads = squadService.getSquads(principal.getId());
        return ResponseEntity.ok(squads);
    }

    @GetMapping("/{squadId}")
    public ResponseEntity<?> read(@AuthenticationPrincipal CustomPrincipal principal,
                                  @PathVariable Integer squadId) {
        User user = userRepository.findById(principal.getId()).get();
        Squad squad = squadRepository.findById(squadId).orElseThrow(() ->
                new IllegalArgumentException("no such squad"));
        if (squad.getUser().getId().equals(user.getId())) {
            SquadDto currentSquad = squadService.getCurrentSquad(squad.getId());
            return ResponseEntity.ok(currentSquad);
        } else {
            SquadDto lockedInSquad = squadService.getLockedInSquad(squad.getId());
            return ResponseEntity.ok(lockedInSquad);
        }
    }

    @PutMapping("/{squadId}")
    public ResponseEntity<?> update(@AuthenticationPrincipal CustomPrincipal principal,
                                    @PathVariable("squadId") Integer squadId,
                                    @RequestBody SquadDto squadDto) {
        User user = userRepository.findById(principal.getId()).get();
        SquadDto dto = squadService.setSquad(squadId, squadDto, user.getId());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{squadId}/history/{matchId}")
    public ResponseEntity<?> getHistory(@PathVariable Integer matchId,
                                        @PathVariable Integer squadId) {
        SquadDto squadDto = squadService.getLockedInSquad(squadId, matchId);
        return ResponseEntity.ok(squadDto);
    }
}
