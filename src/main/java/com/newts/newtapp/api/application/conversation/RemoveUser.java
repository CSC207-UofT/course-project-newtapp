package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.ConversationRepository;
import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.RequestField;
import com.newts.newtapp.api.application.RequestModel;
import com.newts.newtapp.api.errors.ConversationNotFound;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.api.errors.UserNotFoundInConversation;
import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.User;

public class RemoveUser extends ConversationInteractor<Void, Exception> {
    ConversationRepository conversationRepository;
    UserRepository userRepository;

    /**
     * Initiaize a new RemoveUser interactor with supplied conversation and user repositories
     * @param conversationRepository
     * @param userRepository
     */
    public RemoveUser(ConversationRepository conversationRepository,
                      UserRepository userRepository){
        super(conversationRepository, userRepository);
    }
    /**
     * Accepts a RemoveUser request.
     * Remove a User from a Conversation.
     * @param request a request stored as a RequestModel
     */
    public Void request(RequestModel request) throws UserNotFound, ConversationNotFound, UserNotFoundInConversation {
        int userID = (int)request.get(RequestField.USERID);
        int conversationID = (int)request.get(RequestField.CONVERSATION_ID);

        // Fetching the conversation from which we remove user
        Conversation conversation = conversationRepository.findById(conversationID).orElseThrow(ConversationNotFound::new);

        //Fetching the user to remove from conversation
        User user = userRepository.findById(userID).orElseThrow(UserNotFound::new);

        // Check that the user is in the conversation
        if (conversation.getUsers().contains(user)) {
            // Remove the user from the conversation
            conversation.removeUser(user);
            user.removeConversation(conversation);
            conversationRepository.save(conversation);
            userRepository.save(user);
            return null;
        } else {
            throw new UserNotFoundInConversation();
        }
    }
}
