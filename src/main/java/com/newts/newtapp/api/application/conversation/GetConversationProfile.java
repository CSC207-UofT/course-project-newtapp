package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.application.datatransfer.ConversationProfile;
import com.newts.newtapp.api.errors.ConversationNotFound;
import com.newts.newtapp.api.gateways.ConversationRepository;
import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.entities.Conversation;

/**
 * Get a ConversationProfile for a given Conversation.
 */
public class GetConversationProfile extends ConversationInteractor<ConversationProfile, ConversationNotFound>{

    /**
     * Construct a new GetConversationProfile interactor with given ConversationRepository.
     * @param repository    ConversationRepository with user data
     */
    public GetConversationProfile(ConversationRepository repository) {
        super(repository);
    }

    /**
     * Handles a GetConversationProfile request.
     * @param request           a request stored as a RequestModel containing Conversation id
     * @return                  a new ConversationProfile for the given Conversation
     * @throws ConversationNotFound     if Conversation does not exist
     */
    @Override
    public ConversationProfile request(RequestModel request) throws ConversationNotFound {
        int id = (int) request.get(RequestField.CONVERSATION_ID);
        Conversation conversation = conversationRepository.findById(id).orElseThrow(ConversationNotFound::new);
        return new ConversationProfile(conversation);
    }
}
