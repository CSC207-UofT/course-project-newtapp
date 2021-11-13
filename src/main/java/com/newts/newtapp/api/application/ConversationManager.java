package com.newts.newtapp.api.application;

import com.newts.newtapp.api.ConversationRepository;
import com.newts.newtapp.api.MessageRepository;
import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.conversation.*;
import com.newts.newtapp.api.errors.ConversationNotFound;
import com.newts.newtapp.api.errors.EmptyMessage;
import com.newts.newtapp.api.errors.UserNotFound;

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

    ConversationRepository conversationRepository;
    MessageRepository messageRepository;
    UserRepository userRepository;
    /**
     * Creates a conversation using request
     * @param request the filled in RequestModel
     */
    public void createConversation(RequestModel request) throws Exception {
        Create create = new Create(conversationRepository);
        create.request(request);
    }

    /**
     * Changes the status of a conversation specified by request
     * @param request the filled in RequestModel
     */
    public void changeConversationStatus(RequestModel request) throws Exception {
        ChangeStatus changeStatus = new ChangeStatus(conversationRepository);
        changeStatus.request(request);
    }

    /**
     * Adds the user specified by request to the conversation
     * @param request the filled in RequestModel
     */
    public void addUser(RequestModel request) throws UserNotFound, ConversationNotFound, UserBelowMinimumRating, ConversationFull {
        AddUser addUser = new AddUser(conversationRepository, userRepository);
        addUser.request(request);
    }

    /**
     * Gets the user list of a conversation and outputs it using a ResponseModel
     * @param request the filled in RequestModel
     */
    public void getUserList(RequestModel request) throws Exception {
        GetUserList getUserList= new GetUserList(conversationRepository, userRepository);
        getUserList.request(request);
    }

    /**
     * Removes the user specified by request to the conversation
     * @param request the filled in RequestModel
     */
    public void removeUser(RequestModel request) throws Exception {
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
