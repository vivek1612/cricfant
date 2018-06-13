package com.cricfant.repository;

import com.cricfant.model.MatchPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MatchPerfRepository extends JpaRepository<MatchPerformance, Integer> {
    Set<MatchPerformance> findAllByMatchId(Integer matchId);

    MatchPerformance findByTournamentTeamPlayerIdAndMatchId(Integer tournamentTeamPlayerId, Integer matchId);
}
