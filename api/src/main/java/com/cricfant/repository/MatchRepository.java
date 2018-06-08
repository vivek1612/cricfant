package com.cricfant.repository;

import com.cricfant.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Integer> {
    List<Match> findAllByTournamentId(Integer tournamentId);
}
