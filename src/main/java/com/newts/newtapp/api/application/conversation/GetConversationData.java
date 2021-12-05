package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.application.datatransfer.ConversationData;
import com.newts.newtapp.api.application.datatransfer.MessageData;
import com.newts.newtapp.api.application.datatransfer.UserProfile;
import com.newts.newtapp.api.application.user.GetProfileById;
import com.newts.newtapp.api.errors.ConversationNotFound;
import com.newts.newtapp.api.errors.IncorrectPassword;
import com.newts.newtapp.api.errors.MessageNotFound;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.api.gateways.ConversationRepository;
import com.newts.newtapp.api.gateways.MessageRepository;
import com.newts.newtapp.api.gateways.UserRepository;
import com.newts.newtapp.entities.Conversation;

import java.util.ArrayList;

public class GetConversationData extends ConversationInteractor<ConversationData, Exception> {

    /**
     * Creates a GetConversationData Interactor with given User and Conversation Repositories
     * @param userRepository UserRepository containing User data
     * @param conversationRepository ConversationRepository containing COnversation data
     */
    public GetConversationData(ConversationRepository conversationRepository, MessageRepository messageRepository,
                               UserRepository userRepository){
        super(conversationRepository, messageRepository, userRepository);
    }

    /**
     * Accepts a request.
     *
     * @param request a request stored as a RequestModel
     */
    @Override
    public ConversationData request(RequestModel request) throws IncorrectPassword, UserNotFound, ConversationNotFound, MessageNotFound {
        RequestModel requestModel = new RequestModel();
        GetProfileById getProfileById = new GetProfileById(userRepository);
        GetMessageData getMessageData = new GetMessageData(conversationRepository, messageRepository);

        int conversationId = (int) request.get(RequestField.CONVERSATION_ID);
        ArrayList<UserProfile> userProfiles = new ArrayList<>();
        ArrayList<MessageData> messageData = new ArrayList<>();
        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(ConversationNotFound::new);

        // Creating UserProfile objects from list of userids in Conversation and adding them to userProfiles
        // ArrayList
        for(Integer userId:conversation.getUsers()){
            requestModel.fill(RequestField.USER_ID, userId);
            userProfiles.add(getProfileById.request(requestModel));
        }

        // Creating MessageData objects from list of messageIds and adding them to messageData ArrayList
        for(int m:conversation.getMessages()){
            requestModel.fill(RequestField.MESSAGE_ID, m);
            messageData.add(getMessageData.request(requestModel));
        }
        return new ConversationData(messageData, userProfiles, conversation);
    }
}
