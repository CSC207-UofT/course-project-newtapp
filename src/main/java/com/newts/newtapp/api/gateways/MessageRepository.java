package com.newts.newtapp.api.gateways;

import com.newts.newtapp.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * MessageRepository implementation is handled by Spring Boot.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
