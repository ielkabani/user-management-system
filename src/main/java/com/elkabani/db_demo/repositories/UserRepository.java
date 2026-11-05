package com.elkabani.db_demo.repositories;

import com.elkabani.db_demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Find user by email address
     * Used for login authentication
     */
    Optional<User> findByEmail(String email);
}
