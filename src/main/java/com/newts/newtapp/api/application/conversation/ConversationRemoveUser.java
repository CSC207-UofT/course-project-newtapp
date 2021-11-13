package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.User;

public class ConversationRemoveUser extends ConversationInteractor {
    private Conversation conversation;
    private User user;

    /**
     * Accepts a request.
     * Remove a User from a Conversation.
     *
     * @param request a request stored as a RequestModel
     */
    @Override
    public void request(RequestModel request) {
        // Fetching the conversation from which we remove user
        conversation = DataBase.getConversation((String) request.get(RequestField.CONVERSATION_ID));

        //Fetching the user to remove from conversation
        user = DataBase.getUser((String) request.get(RequestField.USERNAME));
        ResponseModel response = new ResponseModel();
        ConfigReader config = (ConfigReader) request.get(RequestField.CONFIG);

        // Check that the user is in the conversation
        if (conversation.getUsers().contains(user)) {
            // Remove the user from the conversation
            conversation.removeUser(user);
            user.removeConversation(conversation);
            response.fill(ResponseField.SUCCESS, user.getUsername() + config.get("removedUser"));
        } else {
            // // Output an error since user not in conversation
            response.fill(ResponseField.FAILURE, user.getUsername() + config.get("userNotInConversation"));
        }
        // send response through provided output boundary
        request.getOutput().respond(response);
    }

    /**
     * Returns the user being removed from the conversation by ConversationRemoveUser and Null otherwise.
     * @return The user being removed the conversation
     */
    public User getUser() {
        return user;
    }

    /**
     * Returns the conversation that the user is being removed from by ConversationRemoveUser and Null otherwise.
     * @return The conversation that the user is being removed from.
     */
    public Conversation getConversation() {
        return conversation;
    }
}
