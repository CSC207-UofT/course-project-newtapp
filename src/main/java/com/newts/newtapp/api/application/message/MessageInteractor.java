package com.newts.newtapp.api.application.message;
import com.newts.newtapp.api.ConversationRepository;
import com.newts.newtapp.api.MessageRepository;
import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.InputBoundary;
import com.newts.newtapp.entities.Message;

/**
 * An abstract MessageInteractor object. Generally to be extended as a specific Message usecase.
 * Requires simply that an implementing class stores a Message object and handles requests.
 */
public abstract class MessageInteractor<ReturnType, ExceptionType extends Exception>
        implements InputBoundary<ReturnType, ExceptionType> {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    /**
     * Initialize a new MessageInteractor with given repository.
     * @param messageRepository     MessageRepository for Message data access
     */
    public MessageInteractor(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = null;
    }

    /**
     * Initialize a new MessageInteractor with given repositories.
     * @param messageRepository     MessageRepository for Message data access
     * @param userRepository        UserRepository for User data access
     */
    public MessageInteractor(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }
}
