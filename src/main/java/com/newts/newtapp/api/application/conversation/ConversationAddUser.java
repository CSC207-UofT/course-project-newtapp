package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.User;

public class ConversationAddUser extends ConversationInteractor {
    Conversation conversation;
    User user;

    /**
     * Accepts a request.
     *
     * @param request a request stored as a RequestModel
     */
    @Override
    public void request(RequestModel request) {
        // Fetching the conversation that the user is requesting to be added to
        conversation = DataBase.getConversation((String) request.get(RequestField.CONVERSATION_ID));

        //Fetching the user which is trying to be added to the conversation
        user = DataBase.getUser((String) request.get(RequestField.USERNAME));
        ResponseModel response = new ResponseModel();
        ConfigReader config = (ConfigReader) request.get(RequestField.CONFIG);

        if (user.getRating() < conversation.getMinRating()){
            // Outputs an error if the user has a lower than necessary rating to join the conversation.
            response.fill(ResponseField.FAILURE, config.get("belowMinimumRating"));
        } else if(conversation.getNumUsers() >= conversation.getMaxSize()){
            // Outputs an error if the conversation already has the maximum number of users
            response.fill(ResponseField.FAILURE, config.get("conversationFull"));
        } else{
            // Adds user to the conversation;
            conversation.addUser(user);
            user.addConversation(conversation);
            response.fill(ResponseField.SUCCESS, user.getUsername() + (config.get("userJoinedConversation")) + conversation.getTitle());
        }
        // send response through provided output boundary
        request.getOutput().respond(response);
    }

    /**
     * Returns the user being added to the conversation by this interactor and Null otherwise.
     * @return The user being added to the conversation
     */
    public User getUser() {
        return user;
    }

    /**
     * Returns the conversation that the user is being added to by this interactor and Null otherwise.
     * @return The conversation that the user is being added to.
     */
    public Conversation getConversation() {
        return conversation;
    }
}
