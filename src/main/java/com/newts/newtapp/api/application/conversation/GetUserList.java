package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.application.datatransfer.UserProfile;
import com.newts.newtapp.api.gateways.ConversationRepository;
import com.newts.newtapp.api.gateways.UserRepository;
import com.newts.newtapp.api.errors.ConversationNotFound;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.User;

import java.util.ArrayList;

/**
 * ConversationInteractor that returns the list of user profiles of the conversation.
 * RequestModel must provide the conversation id
 */
public class GetUserList extends ConversationInteractor<ArrayList<UserProfile>, Exception> {

    /**
     * Initialize a new GetUserList interactor with given ConversationRepository.
     * @param userRepository UserRepository to access user data by
     * @param conversationRepository ConversationRepository to access conversation data by
     */
    public GetUserList(ConversationRepository conversationRepository, UserRepository userRepository) {
        super(conversationRepository, userRepository); }

    /**
     * Returns the list of user profiles of the conversation.
     * @param  request              a request stored as a RequestModel
     * @return userProfiles         a list of user profiles of the conversation
     * @throws ConversationNotFound if conversation does not exist
     * @throws UserNotFound         if user does not exist
     */
    @Override
    public ArrayList<UserProfile> request(RequestModel request) throws ConversationNotFound, UserNotFound {
        int conversationId = (int) request.get(RequestField.CONVERSATION_ID);

        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(ConversationNotFound::new);

        ArrayList<UserProfile> userProfiles = new ArrayList<>();

        // Get the user profiles of the users
        for (int id: conversation.getUsers()){
            // Retrieve a user from DataBase and add its profile to userProfiles
            User user = userRepository.findById(id).orElseThrow(UserNotFound::new);
            userProfiles.add(new UserProfile(user));
        }
        return userProfiles;
    }
}
