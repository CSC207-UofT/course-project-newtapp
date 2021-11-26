package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.application.*;
import com.newts.newtapp.api.errors.ConversationFull;
import com.newts.newtapp.api.errors.ConversationNotFound;
import com.newts.newtapp.api.errors.UserBelowMinimumRating;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.User;
import com.newts.newtapp.api.gateways.ConversationRepository;
import com.newts.newtapp.api.gateways.UserRepository;

/**
 * ConversationInteractor that adds a user to a conversation.
 * RequestModel must provide the conversation id and the user id.
 */
public class AddUser extends ConversationInteractor<Void, Exception> {

    /**
     * Initialize a new AddUser interactor with given ConversationRepository.
     * @param conversationRepository ConversationRepository containing conversation data.
     * @param userRepository         User repository containing user data.
     */
    public AddUser(ConversationRepository conversationRepository,
                   UserRepository userRepository) {
        super(conversationRepository, userRepository);}

    /**
     * Accepts an AddUser request.
     * @param request a request stored as a RequestModel
     */
    @Override
    public Void request(RequestModel request) throws UserNotFound, ConversationNotFound, UserBelowMinimumRating, ConversationFull {
        int conversationId = (int) request.get(RequestField.CONVERSATION_ID);
        int userId = (int) request.get(RequestField.USER_ID);

        // Fetching the conversation
        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(ConversationNotFound::new);

        // Fetching the user to be added
        User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);

        // Check if the User is above the conversation minimum rating
        if (user.getRating() < conversation.getMinRating()) {
            throw new UserBelowMinimumRating();
        }

        // Check if the conversation is full
        if (conversation.getNumUsers() >= conversation.getMaxSize()) {
            throw new ConversationFull();
        }

        // Add User to the conversation and the conversation to the user's list of conversations
        conversation.addUser(user);
        user.addConversation(conversation);

        // Save conversation and save user
        conversationRepository.save(conversation);
        userRepository.save(user);
        return null;
    }
}
