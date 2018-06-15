package com.cricfant.repository;

import com.cricfant.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Integer> {
    Set<Tournament> findAllByActive(boolean active);
}
