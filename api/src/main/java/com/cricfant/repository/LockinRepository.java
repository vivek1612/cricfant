package com.cricfant.repository;

import com.cricfant.model.Lockin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface LockinRepository extends JpaRepository<Lockin, Integer> {
    public Set<Lockin> findAllByMatchIdAndSquadId(Integer matchId, Integer squadId);
}
