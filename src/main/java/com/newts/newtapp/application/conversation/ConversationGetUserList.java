package com.newts.newtapp.application.conversation;

import com.newts.newtapp.application.*;
import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.User;

import java.util.ArrayList;

public class ConversationGetUserList extends ConversationInteractor {
    private Conversation conversation;

    /**
     * Accepts a request.
     *
     * @param request a request stored as a RequestModel
     */
    @Override
    public void request(RequestModel request) {
        // Fetching the conversation from which to get the user list
        conversation = DataBase.getConversation((String) request.get(RequestField.CONVERSATION_ID));
        ResponseModel response = new ResponseModel();
        ConfigReader config = (ConfigReader) request.get(RequestField.CONFIG);

        // Check if conversation is empty
        if (conversation.getNumUsers() == 0) {
            // Output empty conversation message
            response.fill(ResponseField.SUCCESS, config.get("emptyConversation"));
        } else {
            ArrayList<String> usernames = new ArrayList<>();
            // Get the usernames of the users
            // TODO: Change how Conversation store users
            for (User user: conversation.getUsers()){
                // Retrieve a username from DataBase and add it to usernames
                usernames.add(DataBase.getUser(user.getUsername()).getUsername());
            }
            // Output usernames of users in a conversation
            response.fill(ResponseField.SUCCESS, usernames + config.get("inConversation"));
        }
        // Send response through provided output boundary
        request.getOutput().respond(response);
    }

    /**
     * Returns the conversation from which to get the user list or null otherwise.
     * @return the conversation from which to get the user list.
     */
    public Conversation getConversation() {
        return conversation;
    }
}
