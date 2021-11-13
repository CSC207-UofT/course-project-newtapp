package com.newts.newtapp.api.application;

import com.newts.newtapp.api.ConversationRepository;
import com.newts.newtapp.api.MessageRepository;
import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.conversation.*;
import com.newts.newtapp.api.errors.ConversationNotFound;
import com.newts.newtapp.api.errors.EmptyMessage;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.entities.Message;

/**
 * An object representing a ConversationManager of the application.
 */
public class ConversationManager {

    ConversationRepository conversationRepository;
    MessageRepository messageRepository;
    UserRepository userRepository;
    /**
     * Creates a conversation using request
     * @param request the filled in RequestModel
     */
    public void createConversation(RequestModel request) {
        CreateConversation createConversation = new CreateConversation();
        createConversation.request(request);
    }

    /**
     * Changes the status of a conversation specified by request
     * @param request the filled in RequestModel
     */
    public void changeConversationStatus(RequestModel request) {
        ChangeConversationStatus changeConversationStatus = new ChangeConversationStatus();
        changeConversationStatus.request(request);
    }

    /**
     * Adds the user specified by request to the conversation
     * @param request the filled in RequestModel
     */
    public void addUser(RequestModel request) {
        ConversationAddUser conversationAddUser = new ConversationAddUser();
        conversationAddUser.request(request);
    }

    /**
     * Gets the user list of a conversation and outputs it using a ResponseModel
     * @param request the filled in RequestModel
     */
    public void getUserList(RequestModel request) {
        ConversationGetUserList conversationGetUserList= new ConversationGetUserList();
        conversationGetUserList.request(request);
    }

    /**
     * Removes the user specified by request to the conversation
     * @param request the filled in RequestModel
     */
    public void removeUser(RequestModel request) {
        ConversationRemoveUser conversationRemoveUser = new ConversationRemoveUser();
        conversationRemoveUser.request(request);
    }

    /**
     * Adds message to the conversation specified by the user
     * @param request the filled in RequestModel
     */
    public void AddMessage(RequestModel request) throws UserNotFound, ConversationNotFound, EmptyMessage{
        AddMessage addMessage = new AddMessage(conversationRepository, messageRepository, userRepository);
        addMessage.request(request);
    }

    //TODO: Whatever needs to be done with regards to the conversationqueue stuff
}
