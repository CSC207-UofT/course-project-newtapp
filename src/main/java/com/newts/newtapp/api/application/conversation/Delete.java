package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.errors.*;
import com.newts.newtapp.api.gateways.ConversationRepository;
import com.newts.newtapp.api.gateways.MessageRepository;
import com.newts.newtapp.api.gateways.UserRepository;
import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.User;


public class Delete extends ConversationInteractor<Void,Exception> {

    /**
     * Creates a Delete conversation interactor for deleting conversations
     * @param conversationRepository ConversationRepository containing conversation data
     * @param messageRepository MessageRepository containing message data
     * @param userRepository UserRepository containing user data
     */
    public Delete(ConversationRepository conversationRepository,
                  MessageRepository messageRepository,
                  UserRepository userRepository) { super(conversationRepository,
            messageRepository,
            userRepository); }

    /**
     * Accepts a Delete conversation Request
     * @param request   a request stored as a RequestModel
     */
    @Override
    public Void request(RequestModel request) throws UserNotFound, IncorrectPassword, ConversationNotFound, WrongAuthor {
        int conversationID = (int) request.get(RequestField.CONVERSATION_ID);
        int userID = (int) request.get(RequestField.USER_ID);

        Conversation conversation = conversationRepository.findById(conversationID).orElseThrow(ConversationNotFound::new);

        //Check if the conversation is created by the given userID
        if (conversation.getAuthorId() != userID)  {
            throw new WrongAuthor();
        }

        // Loops through list of users of the conversation and removes conversation from their conversation list
        for (int userId : conversation.getUsers()){
            User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);
            user.removeConversation(conversation);
            userRepository.save(user);
        }

        // Deleting all associated messages from the MessageRepository
        for (int messagedId : conversation.getMessages()){
            messageRepository.delete(messageRepository.getById(messagedId));
        }


        conversationRepository.delete(conversation);
        return null;
    }
}