package com.newts.newtapp.api.application.user;
import com.newts.newtapp.api.ConversationRepository;
import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.InputBoundary;

/**
 * An abstract UserInteractor object. Generally to be extended as a specific User usecase.
 * Requires simply that an implementing class stores a User object and handles requests.
 */
public abstract class UserInteractor<ReturnType, ExceptionType extends Exception>
        implements InputBoundary<ReturnType, ExceptionType> {
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;

    /**
     * Initialize a new UserInteractor with given repository.
     * @param userRepository            UserRepository for User data access
     */
    public UserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.conversationRepository = null;
    }

    /**
     * Initialize a new UserInteractor with given repositories.
     * @param userRepository            UserRepository for User data access
     * @param conversationRepository    ConversationRepository for Conversation data access
     */
    public UserInteractor(UserRepository userRepository, ConversationRepository conversationRepository) {
        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
    }
}
