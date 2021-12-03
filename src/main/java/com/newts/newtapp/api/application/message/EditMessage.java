package com.newts.newtapp.api.application.message;


import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.errors.*;
import com.newts.newtapp.api.gateways.MessageRepository;
import com.newts.newtapp.api.gateways.UserRepository;
import com.newts.newtapp.entities.Message;

public class EditMessage extends MessageInteractor<Void, Exception> {

    /**
     * Initialize a new EditMessage interactor with supplied user and message repositories
     * @param messageRepository MessageRepository which contains message data
     * @param userRepository UserRepository which contains user data
     */
    public EditMessage(MessageRepository messageRepository, UserRepository userRepository){
        super(messageRepository, userRepository);
    }

    /**
     * Accepts a EditMessage request.
     * Edits a Message.
     * @param request a request stored as a RequestModel
     */
    public Void request(RequestModel request) throws MessageNotFound, WrongAuthor, EmptyMessage {
        int messageID = (int) request.get(RequestField.MESSAGE_ID);
        int userID = (int) request.get(RequestField.USER_ID);
        String messageBody = ((String) request.get(RequestField.MESSAGE_BODY));

        //Fetching the message to remove from conversation
        Message message = messageRepository.findById(messageID).orElseThrow(MessageNotFound::new);

        //Check if the message is written by the given userID
        if (message.getAuthor() != userID)  {
            throw new WrongAuthor();
        }

        ///Checks if message is empty
        if(messageBody.isEmpty()){
            throw new EmptyMessage();
        }
        else{
            message.setBody(messageBody);
            messageRepository.save(message);
            return null;
        }
    }
}

