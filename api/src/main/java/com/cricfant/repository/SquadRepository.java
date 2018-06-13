package com.cricfant.repository;

import com.cricfant.model.Squad;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SquadRepository extends JpaRepository<Squad, Integer> {

    @EntityGraph(attributePaths = {"leagues", "tournament"},
            type = EntityGraph.EntityGraphType.LOAD)
    Set<Squad> findAllByUserId(Integer userId);

    @EntityGraph(attributePaths = {"leagues", "tournament", "squadPlayers"},
            type = EntityGraph.EntityGraphType.LOAD)
    Optional<Squad> findById(Integer id);
}
