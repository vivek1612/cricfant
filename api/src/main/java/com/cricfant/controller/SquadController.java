package com.cricfant.controller;

import com.cricfant.dto.SquadDto;
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

@SuppressWarnings("Duplicates")
@RestController
@RequestMapping("/squad")
public class SquadController {

    @Autowired
    SquadService squadService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SquadRepository squadRepository;
    @Autowired
    MatchService matchService;

    @GetMapping
    public ResponseEntity<?> readSquads(@AuthenticationPrincipal String principal) {
        User user = userRepository.findByEmail(principal).get();
        List<SquadDto> squads = squadService.getSquads(user.getId());
        return ResponseEntity.ok(squads);
    }

    @PostMapping("/{squadId}")
    public ResponseEntity<?> setSquad(@AuthenticationPrincipal String principal,
                                      @PathVariable("squadId") Integer squadId,
                                      @RequestBody SquadDto squadDto) {
        User user = userRepository.findByEmail(principal).get();
        SquadDto dto = squadService.setSquad(squadId, squadDto, user.getId());
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> createSquad(@AuthenticationPrincipal String principal,
                                         @RequestBody SquadDto squadDto) {
        User user = userRepository.findByEmail(principal).get();
        SquadDto dto = squadService.createSquad(user.getId(), squadDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{squadId}")
    public ResponseEntity<?> readSquad(@AuthenticationPrincipal String principal,
                                       @PathVariable Integer squadId) {
        User user = userRepository.findByEmail(principal).get();
        SquadDto s = squadService.getSquad(squadId, user.getId());
        return ResponseEntity.ok(s);
    }

    @GetMapping("/{squadId}/getSquadHistory")
    public ResponseEntity<?> getSquadHistory(@RequestParam Integer matchId,
                                             @PathVariable Integer squadId) {
        SquadDto squadDto = squadService.getSquadHistory(matchId, squadId);
        return ResponseEntity.ok(squadDto);
    }
}
