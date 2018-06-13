package com.cricfant.repository;

import com.cricfant.model.SquadPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SquadPlayerRepository extends JpaRepository<SquadPlayer, Integer> {
}
