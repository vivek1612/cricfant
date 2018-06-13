package com.cricfant.repository;

import com.cricfant.model.Lockin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LockinRepository extends JpaRepository<Lockin, Integer> {
    Set<Lockin> findAllByMatchIdAndSquadId(Integer matchId, Integer squadId);
}
