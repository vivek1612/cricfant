package com.cricfant.controller;

import com.cricfant.dto.CustomPrincipal;
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

import javax.validation.Valid;
import java.util.List;

@SuppressWarnings("Duplicates")
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

    @GetMapping
    public ResponseEntity<?> readSquads(@AuthenticationPrincipal CustomPrincipal principal) {
        List<SquadDto> squads = squadService.getSquads(principal.getId());
        return ResponseEntity.ok(squads);
    }

    @PutMapping("/{squadId}")
    public ResponseEntity<?> setSquad(@AuthenticationPrincipal CustomPrincipal principal,
                                      @PathVariable("squadId") Integer squadId,
                                      @RequestBody SquadDto squadDto) {
        User user = userRepository.findById(principal.getId()).get();
        SquadDto dto = squadService.setSquad(squadId, squadDto, user.getId());
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> createSquad(@AuthenticationPrincipal CustomPrincipal principal,
                                         @RequestBody SquadDto squadDto) {
        User user = userRepository.findById(principal.getId()).get();
        SquadDto dto = squadService.createSquad(user.getId(), squadDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{squadId}")
    public ResponseEntity<?> readSquad(@AuthenticationPrincipal CustomPrincipal principal,
                                       @PathVariable Integer squadId) {
        User user = userRepository.findById(principal.getId()).get();
        SquadDto s = squadService.getSquad(squadId, user.getId());
        return ResponseEntity.ok(s);
    }

    @GetMapping("/{squadId}/history/{matchId}")
    public ResponseEntity<?> getSquadHistory(@PathVariable Integer matchId,
                                             @PathVariable Integer squadId) {
        SquadDto squadDto = squadService.getSquadHistory(matchId, squadId);
        return ResponseEntity.ok(squadDto);
    }
}
