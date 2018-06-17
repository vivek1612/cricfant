package com.cricfant.repository;

import com.cricfant.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {
    List<Match> findAllByTournamentId(Integer tournamentId);

    /**
     *
     * @param tournamentId
     * @return 5 Upcoming Matches
     */
    Set<Match> findTop5ByTournamentIdAndLockedInFalseOrderBySeqNum(Integer tournamentId);

    /**
     *
     * @param tournamentId
     * @return Next Match
     */
    Match findTopByTournamentIdAndLockedInIsFalseOrderBySeqNum(Integer tournamentId);

    /**
     *
     * @param tournamentId
     * @return Last Match
     */
    Match findTopByTournamentIdAndLockedInIsTrueOrderBySeqNumDesc(Integer tournamentId);
}
