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
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<?> readSquads(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).get();
//        user = userRepository.findByEmail("vivek.1612@gmail.com");
        List<SquadDto> squads = squadService.getSquads(user.getId());
        return ResponseEntity.ok(squads);
    }

    @PostMapping("/{squadId}")
    public ResponseEntity<?> setSquad(@AuthenticationPrincipal UserDetails userDetails,
                                      @PathVariable("squadId") Integer squadId,
                                      @RequestBody SquadDto squadDto) {
        User user = userRepository.findByEmail(userDetails.getUsername()).get();
//        User user = userRepository.findByEmail("vivek.1612@gmail.com");
        SquadDto dto = squadService.setSquad(squadId, squadDto, user.getId());
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> createSquad(@AuthenticationPrincipal UserDetails userDetails,
                                         @RequestBody SquadDto squadDto) {
        User user = userRepository.findByEmail(userDetails.getUsername()).get();
//        User user = userRepository.findByEmail("vivek.1612@gmail.com");
        SquadDto dto = squadService.createSquad(user.getId(), squadDto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{squadId}")
    public ResponseEntity<?> readSquad(@AuthenticationPrincipal UserDetails userDetails,
                                       @PathVariable Integer squadId) {
        User user = userRepository.findByEmail(userDetails.getUsername()).get();
//        User user = userRepository.findByEmail("vivek.1612@gmail.com");
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
