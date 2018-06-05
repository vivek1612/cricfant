package com.cricfant.repository;

import com.cricfant.model.Squad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SquadRepository extends JpaRepository<Squad,Integer> {
}
