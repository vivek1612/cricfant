package com.cricfant.repository;

import com.cricfant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    Optional<User> findByUid(String uid);
}
