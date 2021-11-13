package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.ConversationRepository;
import com.newts.newtapp.api.application.*;
import com.newts.newtapp.api.errors.ConversationNotFound;
import com.newts.newtapp.entities.Conversation;

/**
 * ConversationInteractor that changes the status of the conversation.
 * RequestModel must provide the conversation id
 */
public class ChangeStatus extends ConversationInteractor<Void, Exception> {
    private ConversationRepository repository;

    /**
     * Initialize a new ChangeStatus interactor with given ConversationRepository.
     * @param repository ConversationRepository to access conversation data by
     */
    public ChangeStatus(ConversationRepository repository) { super(repository); }

    /**
     * Changes the status of the conversation with the id in the request
     *
     * @param request a request stored as a RequestModel
     */
    @Override
    public Void request(RequestModel request) throws ConversationNotFound {
        int conversationId = (int) request.get(RequestField.ID);

        Conversation conversation = repository.findById(conversationId).orElseThrow(ConversationNotFound::new);

        // Change the status of the conversation
        conversation.toggleIsOpen();
        repository.save(conversation);
        return null;
    }
}
