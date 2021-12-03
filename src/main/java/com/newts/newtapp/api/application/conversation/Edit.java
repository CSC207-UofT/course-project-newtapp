package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.errors.ConversationNotFound;
import com.newts.newtapp.api.gateways.ConversationRepository;
import com.newts.newtapp.entities.Conversation;

import java.util.ArrayList;

public class Edit extends ConversationInteractor<Void,Exception>{

    /**
     * Initialize a new Edit interactor with given ConversationRepository.
     * @param conversationRepository ConversationRepository to access Conversation data by
     */
    public Edit(ConversationRepository conversationRepository){super(conversationRepository);}

    /**
     * Accepts a request.
     *
     * @param request a request stored as a RequestModel
     */
    @Override
    public Void request(RequestModel request) throws ConversationNotFound {
        int conversationID = (int) request.get(RequestField.CONVERSATION_ID);

        Conversation conversation = conversationRepository.findById(conversationID).orElseThrow(ConversationNotFound::new);


        String title = (String) request.get(RequestField.TITLE);
        ArrayList<String> topics = (ArrayList<String>) request.get(RequestField.TOPIC);
        String location = (String) request.get(RequestField.LOCATION);
        int locationRadius = (int) request.get(RequestField.LOCATION_RADIUS);
        int minRating = (int) request.get(RequestField.MIN_RATING);

        // Sets updated conversation information
        conversation.setTitle(title);
        conversation.setLocation(location);
        conversation.setLocationRadius(locationRadius);
        conversation.setMinRating(minRating);
        conversation.setTopics(topics);
        return null;
    }
}
