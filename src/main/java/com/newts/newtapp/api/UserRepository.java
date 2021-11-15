package com.newts.newtapp.api;

import com.newts.newtapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * UserRepository implementation is handled by Spring Boot.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsernameIgnoreCase(String username);   // this is incredibly cool @Spring Boot Devs

}
