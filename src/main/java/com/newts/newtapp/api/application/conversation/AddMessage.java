package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.ConversationRepository;
import com.newts.newtapp.api.MessageRepository;
import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.RequestField;
import com.newts.newtapp.api.application.RequestModel;
import com.newts.newtapp.api.errors.ConversationNotFound;
import com.newts.newtapp.api.errors.EmptyMessage;
import com.newts.newtapp.entities.*;


public class AddMessage extends ConversationInteractor<Void, Exception> {

    /**
     * Initialize a new AddMessage interactor with supplied message, conversation and user repositories
     * @param messageRepository MessageRepository containing message data
     * @param conversationRepository ConversationRepository containing conversation data
     * @param userRepository UserRepository  containing user data
     */
    public AddMessage(ConversationRepository conversationRepository,
                      MessageRepository messageRepository,
                      UserRepository userRepository){
        super(conversationRepository, messageRepository, userRepository);
    }

    /**
     * Accepts an AddMessage request.
     * @param request a request stored as a RequestModel
     */
    @Override
    public Void request(RequestModel request) throws ConversationNotFound, EmptyMessage {
        int conversationId = (int) request.get(RequestField.CONVERSATION_ID);
        int userId = (int) request.get(RequestField.USER_ID);
        String messageBody = ((String) request.get(RequestField.MESSAGE_BODY));

        // Fetching conversation that the message is being added to and user writing message
        Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(ConversationNotFound::new);

        //Checks if message is empty
        if(messageBody.isEmpty()){
            throw new EmptyMessage();
        }
        else{
            int messageId = ((int) request.get(RequestField.MESSAGE_ID));
            // Write time and Update time are handled within message constructor
            Message message  = new Message(messageId, messageBody, userId);
            conversation.addMessage(message);
            messageRepository.save(message);
            conversationRepository.save(conversation);
            return null;
        }
    }
}