package com.cricfant.repository;

import com.cricfant.model.TournamentTeamPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TournamentTeamPlayerRepository extends JpaRepository<TournamentTeamPlayer, Integer> {
    Optional<TournamentTeamPlayer> findByPlayerExtId(Integer extId);
}
