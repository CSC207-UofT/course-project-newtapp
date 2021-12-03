package com.newts.newtapp.api.gateways;

import com.newts.newtapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository implementation is handled by Spring Boot.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);   // this is incredibly cool @Spring Boot Devs
}
