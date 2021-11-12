package com.newts.newtapp.api;

import com.newts.newtapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository implementation is handled by Spring Boot.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
