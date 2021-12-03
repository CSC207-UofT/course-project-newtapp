package com.newts.newtapp.api.application;

import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.application.datatransfer.ConversationData;
import com.newts.newtapp.api.application.datatransfer.ConversationProfile;
import com.newts.newtapp.api.application.conversation.GetConversationProfile;
import com.newts.newtapp.api.gateways.ConversationRepository;
import com.newts.newtapp.api.gateways.MessageRepository;
import com.newts.newtapp.api.gateways.UserRepository;
import com.newts.newtapp.api.application.conversation.*;
import com.newts.newtapp.api.errors.*;
import org.springframework.context.annotation.Configuration;

/**
 * An object representing a ConversationManager of the application.
 */
@Configuration
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
     * Returns a ConversationProfile given a Conversation id in a RequestModel.
     * @param request                   RequestModel containing Conversation's id
     * @return                          ConversationProfile of corresponding Conversation
     * @throws ConversationNotFound     If Conversation id does not exist
     */
    public ConversationProfile getProfile(RequestModel request) throws ConversationNotFound {
        GetConversationProfile getProfileById = new GetConversationProfile(conversationRepository);
        return getProfileById.request(request);
    }

    /**
     * Returns a ConversationData given a Conversation id in a RequestModel.
     * @param request                   RequestModel containing Conversation's id
     * @return                          ConversationData of corresponding Conversation
     * @throws ConversationNotFound     If Conversation id does not exist
     */
    public ConversationData getData(RequestModel request) throws ConversationNotFound, UserNotFound,
            MessageNotFound, IncorrectPassword {
        GetConversationData getDataById = new GetConversationData(conversationRepository, messageRepository,
                userRepository);
        return getDataById.request(request);
    }

    /**
     * Creates a conversation using request
     * @param request the filled in RequestModel
     */
    public void createConversation(RequestModel request) throws InvalidMinRating, InvalidConversationSize {
        Create create = new Create(conversationRepository);
        create.request(request);
    }

    /**
     * Changes the status of a conversation specified by request
     * @param request the filled in RequestModel
     */
    public void changeConversationStatus(RequestModel request) throws WrongAuthor, ConversationNotFound {
        ChangeStatus changeStatus = new ChangeStatus(conversationRepository);
        changeStatus.request(request);
    }

    /**
     * Edits a conversation using request
     * @param request the filled in RequestModel
     */
    public void editConversation(RequestModel request) throws ConversationNotFound, InvalidMinRating, WrongAuthor,
            InvalidConversationSize {
        Edit edit = new Edit(conversationRepository);
        edit.request(request);
    }

    /**
     * Adds the user specified by request to the conversation
     * @param request the filled in RequestModel
     */
    public void addUser(RequestModel request) throws UserNotFound, ConversationNotFound, UserBelowMinimumRating, ConversationFull, UserBlocked {
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
    public void removeUser(RequestModel request) throws UserNotFound, ConversationNotFound, UserNotFoundInConversation {
        RemoveUser removeUser = new RemoveUser(conversationRepository, userRepository);
        removeUser.request(request);
    }

    /**
     * Adds message to the conversation specified by the user
     * @param request the filled in RequestModel
     */
    public void AddMessage(RequestModel request) throws UserNotFound, ConversationNotFound, EmptyMessage{
        AddMessage addMessage = new AddMessage(conversationRepository, messageRepository, userRepository);
        addMessage.request(request);
    }
}
