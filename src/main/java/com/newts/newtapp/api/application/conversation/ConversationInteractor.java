package com.newts.newtapp.api.application.conversation;
import com.newts.newtapp.api.ConversationRepository;
import com.newts.newtapp.api.application.InputBoundary;
import com.newts.newtapp.entities.Conversation;

/**
 * An abstract ConversationInteractor object. Generally to be extended as a specific Conversation usecase.
 * Requires simply that an implementing class stores a Conversation object and handles requests.
 */
public abstract class ConversationInteractor<ReturnType, ExceptionType extends Exception>
        implements InputBoundary<ReturnType, ExceptionType>{
    private ConversationRepository repository;

    /**
     * Initialize a new ConversationInteractor with given repository.
     * @param repository    ConversationRepository containing conversation data.
     */
    public ConversationInteractor(ConversationRepository repository){this.repository = repository;}
}