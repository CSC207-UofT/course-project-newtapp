package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.application.datatransfer.ConversationProfile;
import com.newts.newtapp.api.errors.ConversationNotFound;
import com.newts.newtapp.api.gateways.ConversationRepository;
import com.newts.newtapp.api.gateways.UserRepository;
import com.newts.newtapp.entities.Conversation;

public class GetConversationProfile extends ConversationInteractor<ConversationProfile, Exception> {

    /**
     * Create a GetConversationProfile interactor with supplied repositories
     * @param conversationRepository ConversatioRepository containing Conversation data
     * @param userRepository UserRepository containing User data
     */
    public GetConversationProfile(ConversationRepository conversationRepository,
                                  UserRepository userRepository){super(conversationRepository, userRepository);}


    /**
     * Accepts a request.
     *
     * @param request a request stored as a RequestModel
     */
    @Override
    public ConversationProfile request(RequestModel request) throws ConversationNotFound {
        int conversationId = (int) request.get(RequestField.CONVERSATION_ID);
        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(ConversationNotFound::new);
        return new ConversationProfile(conversation);
    }
}
