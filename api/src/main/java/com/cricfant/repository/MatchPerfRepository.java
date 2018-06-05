package com.cricfant.repository;

import com.cricfant.model.MatchPerf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MatchPerfRepository extends JpaRepository<MatchPerf, Integer> {
    Set<MatchPerf> findAllByMatchId(Integer matchId);
    MatchPerf findByPlayerIdAndMatchId(Integer playerId, Integer matchId);
}
