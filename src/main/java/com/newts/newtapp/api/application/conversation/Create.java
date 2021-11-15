package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.ConversationRepository;
import com.newts.newtapp.api.application.*;
import com.newts.newtapp.entities.Conversation;

import java.util.ArrayList;

/**
 * ConversationInteractor that creates a new conversation.
 * RequestModel must provide details for a new conversation
 */
public class Create extends ConversationInteractor<Void, Exception> {

    /**
     * Initialize a new Create interactor with given ConversationRepository.
     * @param repository    ConversationRepository to access Conversation data by
     */
    public Create(ConversationRepository repository) { super(repository); }

    /**
     * Accepts a Create request and creates a conversation based on the request
     * @param request   a request stored as a RequestModel
     */
    @Override
    public Void request(RequestModel request) {
        String title = (String) request.get(RequestField.TITLE);
        ArrayList<String> topics = new ArrayList<>();
        topics.add((String) request.get(RequestField.TOPIC));
        String location = (String) request.get(RequestField.LOCATION);
        int locationRadius = (int) request.get(RequestField.LOCATION_RADIUS);
        int minRating = (int) request.get(RequestField.MIN_RATING);
        int maxSize = (int) request.get(RequestField.MAX_SIZE);
        String closingTime = (String) request.get(RequestField.CLOSING_TIME);
        int creatorId = (int) request.get(RequestField.USER_ID);

        Conversation conversation = new Conversation(0, title, topics, location,
                locationRadius, minRating, maxSize, closingTime, creatorId);
        conversationRepository.save(conversation);
        return null;
    }
}
