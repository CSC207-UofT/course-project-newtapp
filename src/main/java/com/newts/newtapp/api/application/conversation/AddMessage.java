package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.entities.*;


public class AddMessage extends ConversationInteractor {
    Message message;
    Conversation conversation;
    /**
     * Accepts a request.
     *
     * @param request a request stored as a RequestModel
     */
    @Override
    public void request(RequestModel request) {
        ResponseModel response = new ResponseModel();
        ConfigReader config = (ConfigReader) request.get(RequestField.CONFIG);


        // Fetching conversation that the message is being added to and user writing message
        conversation = DataBase.getConversation((String) request.get(RequestField.CONVERSATION_ID));
        User user = DataBase.getUser((String) request.get(RequestField.USERNAME));

        String messageBody = ((String) request.get(RequestField.MESSAGE_BODY));

        //Checks if message is empty
        if(messageBody.isEmpty()){
            response.fill(ResponseField.FAILURE, config.get("emptyMessage"));
        }
        else{
            String writeTime = ((String) request.get(RequestField.WRITE_TIME));
            String lastUpdatedTime = ((String) request.get(RequestField.LAST_UPDATED_TIME));
            int messageID = ((int) request.get(RequestField.MESSAGE_ID));
            message  = new Message(messageID, messageBody, user);
            DataBase.addMessage(conversation.getId(), message);
            response.fill(ResponseField.SUCCESS, config.get("messageSent"));
        }
        // send response through provided output boundary
        request.getOutput().respond(response);
    }

    /**
     * Returns the Message that was successfully created this interactor, or null otherwise.
     * @return created Message or null if no message has been created.
     */
    public Message getMessage(){
        return message;
    }
}
