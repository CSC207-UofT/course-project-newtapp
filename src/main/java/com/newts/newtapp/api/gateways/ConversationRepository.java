package com.newts.newtapp.api.gateways;

import com.newts.newtapp.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ConversationRepository implementation is handled by Spring Boot.
 */
@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Integer> {
}
