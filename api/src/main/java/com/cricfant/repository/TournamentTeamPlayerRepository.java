package com.cricfant.repository;

import com.cricfant.model.TournamentTeamPlayer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TournamentTeamPlayerRepository extends JpaRepository<TournamentTeamPlayer, Integer> {
    Optional<TournamentTeamPlayer> findByPlayerExtId(Integer extId);

    @EntityGraph(attributePaths = {"player", "tournamentTeam.team"},
            type = EntityGraph.EntityGraphType.LOAD)
    Set<TournamentTeamPlayer> findAllByTournamentTeam_TournamentId(Integer tournamentId);
}
