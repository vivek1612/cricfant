package com.cricfant.controller;

import com.cricfant.constant.MatchResult;
import com.cricfant.repository.UserRepository;
import com.cricfant.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MatchService matchService;

    @PostMapping("/updateScore")
    public ResponseEntity<?> updateScore(@RequestParam String url,@RequestParam Integer matchId) throws IOException {
        matchService.processScoresForMatch(url,matchId);
        return ResponseEntity.ok("updated");
    }

    @PostMapping("/setResult")
    public ResponseEntity<?> setResult(@RequestParam MatchResult result, @RequestParam Integer matchId) throws IOException {
        matchService.setResultForMatch(result,matchId);
        return ResponseEntity.ok("result set");
    }
}