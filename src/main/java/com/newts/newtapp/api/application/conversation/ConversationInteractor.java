package com.newts.newtapp.api.application.conversation;
import com.newts.newtapp.api.ConversationRepository;
import com.newts.newtapp.api.MessageRepository;
import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.InputBoundary;
import com.newts.newtapp.entities.Conversation;

/**
 * An abstract ConversationInteractor object. Generally to be extended as a specific Conversation usecase.
 * Requires simply that an implementing class stores a Conversation object and handles requests.
 */
public abstract class ConversationInteractor<ReturnType, ExceptionType extends Exception>
        implements InputBoundary<ReturnType, ExceptionType>{
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    /**
     * Initialize a new ConversationInteractor with all repositories.
     * @param conversationRepository ConversationRepository containing conversation data.
     * @param messageRepository MessageRepository containing message data.
     * @param userRepository User repository containing user data.
     */
    public ConversationInteractor(ConversationRepository conversationRepository,
                                  MessageRepository messageRepository,
                                  UserRepository userRepository){
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    /**
     * Initialize a new ConversationInteractor with conversation and user repositories.
     * @param conversationRepository ConversationRepository containing conversation data.
     * @param userRepository UserRepository containing user data.
     */
    public ConversationInteractor(ConversationRepository conversationRepository,
                                  UserRepository userRepository){
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
        this.messageRepository = null;
    }

    /**
     * Initialize a new ConversationInteractor with conversation repository.
     * @param conversationRepository ConversationRepostory containing conversation data.
     */
    public ConversationInteractor(ConversationRepository conversationRepository){
        this.conversationRepository = conversationRepository;
        this.userRepository = null;
        this.messageRepository = null;
    }
}