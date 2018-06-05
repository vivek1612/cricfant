package com.cricfant.repository;

import com.cricfant.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player,Integer>{
    Optional<Player> findByExtId(Integer extId);
}
