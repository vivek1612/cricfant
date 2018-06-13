package com.cricfant.repository;

import com.cricfant.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {
    List<Match> findAllByTournamentId(Integer tournamentId);
}
