package com.newts.newtapp.api.application;

import com.newts.newtapp.api.gateways.MessageRepository;
import com.newts.newtapp.api.gateways.UserRepository;
import org.springframework.context.annotation.Configuration;

/**
 * A facade for message interactors.
 */
@Configuration
public class MessageManager {
    /**
     * The application's repositories to provide database access:
     */
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    /**
     * Initialize a new MessageManager with given repositories.
     * @param messageRepository     MessageRepository to access Message data
     * @param userRepository        UserRepository to access User data
     */
    public MessageManager(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }
}
