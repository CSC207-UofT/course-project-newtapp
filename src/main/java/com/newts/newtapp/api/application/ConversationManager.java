package com.newts.newtapp.api.application;

import com.newts.newtapp.api.ConversationRepository;
import com.newts.newtapp.api.MessageRepository;
import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.conversation.*;
import com.newts.newtapp.api.errors.ConversationFull;
import com.newts.newtapp.api.errors.ConversationNotFound;
import com.newts.newtapp.api.errors.UserBelowMinimumRating;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.entities.Conversation;

/**
 * An object representing a ConversationManager of the application.
 */
public class ConversationManager {
    /**
     * The ConversationRepository, MessageRepository and UserRepository this UserManager is working with.
     */
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    /**
     * Initialize a new ConversationManager with given UserRepository.
     * @param conversationRepository ConversationRepository containing conversation data.
     * @param messageRepository      MessageRepository containing message data.
     * @param userRepository         User repository containing user data.
     */
    public ConversationManager(ConversationRepository conversationRepository,
                               MessageRepository messageRepository,
                               UserRepository userRepository){
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates a conversation using request
     * @param request the filled in RequestModel
     */
    public void createConversation(RequestModel request) throws Exception {
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
    public void addUser(RequestModel request) throws UserNotFound, ConversationNotFound, UserBelowMinimumRating, ConversationFull {
        AddUser conversationAddUser = new AddUser(conversationRepository, userRepository);
        conversationAddUser.request(request);
    }

    /**
     * Gets the user list of a conversation and outputs it using a ResponseModel
     * @param request the filled in RequestModel
     */
    public void getUserList(RequestModel request) throws Exception {
        ConversationGetUserList conversationGetUserList= new ConversationGetUserList();
        conversationGetUserList.request(request);
    }

    /**
     * Removes the user specified by request to the conversation
     * @param request the filled in RequestModel
     */
    public void removeUser(RequestModel request) throws Exception {
        ConversationRemoveUser conversationRemoveUser = new ConversationRemoveUser();
        conversationRemoveUser.request(request);
    }

    //TODO: Whatever needs to be done with regards to the conversationqueue stuff
}