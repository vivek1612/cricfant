package com.cricfant.repository;

import com.cricfant.model.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeagueRepository extends JpaRepository<League,Integer>{
    List<League> findAllByTournament_Id(Integer tournamentId);
}
