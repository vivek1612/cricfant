package com.cricfant.repository;

import com.cricfant.model.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeagueRepository extends JpaRepository<League, Integer> {
    List<League> findAllByTournament_Id(Integer tournamentId);
}
