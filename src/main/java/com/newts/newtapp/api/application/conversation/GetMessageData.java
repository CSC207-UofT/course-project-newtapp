package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.application.datatransfer.MessageData;
import com.newts.newtapp.api.errors.IncorrectPassword;
import com.newts.newtapp.api.errors.MessageNotFound;
import com.newts.newtapp.api.gateways.ConversationRepository;
import com.newts.newtapp.api.gateways.MessageRepository;
import com.newts.newtapp.entities.Message;

public class GetMessageData extends ConversationInteractor<MessageData, Exception>{

    /**
     * Creates new GetMessageData Interactor with supplied Conversation and Message Repositories
     * @param conversationRepository ConversationRepository containing conversation data
     * @param messageRepository MessageRepository containing message data
     */
    public GetMessageData(ConversationRepository conversationRepository, MessageRepository messageRepository){
        super(conversationRepository, messageRepository);}

    /**
     * Accepts a request.
     *
     * @param request a request stored as a RequestModel
     */
    @Override
    public MessageData request(RequestModel request) throws IncorrectPassword, MessageNotFound {
        int id = (int) request.get(RequestField.MESSAGE_ID);
        Message message =  messageRepository.findById(id).orElseThrow(MessageNotFound::new);
        return new MessageData(message);
    }
}
