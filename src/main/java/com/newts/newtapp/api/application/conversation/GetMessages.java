package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.errors.ConversationNotFound;
import com.newts.newtapp.api.errors.MessageNotFound;
import com.newts.newtapp.api.gateways.ConversationRepository;
import com.newts.newtapp.api.application.RequestField;
import com.newts.newtapp.api.application.RequestModel;
import com.newts.newtapp.api.gateways.MessageRepository;
import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.Message;

import java.util.ArrayList;

public class GetMessages extends ConversationInteractor<ArrayList<Message>,Exception>  {

    /**
     * Initialize a new GetMessage interactor with given ConversationRepository.
     * @param conversationRepository    ConversationRepository to access conversation data by
     * @param messageRepository    MessageRepository to access conversation data by
     */
    public GetMessages(ConversationRepository conversationRepository, MessageRepository messageRepository) {
        super(conversationRepository, messageRepository);
    }

    /**
     *
     * @param request   Accepts a GetMessages Request
     * @return An array of Messages
     * @throws ConversationNotFound if the conversation can not be found
     * @throws MessageNotFound if the message can not be found
     */
    @Override
    public ArrayList<Message> request(RequestModel request) throws ConversationNotFound, MessageNotFound {
        int conversationId = (int) request.get(RequestField.CONVERSATION_ID);

        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(ConversationNotFound::new);

        ArrayList<Message> messages = new ArrayList<>();

        // Get the messages of the conversation
        for (int id: conversation.getMessages()){
            // Retrieve a message from DataBase and add it to messages
            Message message = messageRepository.findById(id).orElseThrow(MessageNotFound::new);
            messages.add(message);
        }
        return messages;
    }
}
