package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.application.RequestField;
import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.User;

import java.util.ArrayList;


public class CreateConversation extends ConversationInteractor {
    private Conversation conversation;

    /**
     * Accepts a request and creates a conversation based on the request
     * @param request   a request stored as a RequestModel
     */
    @Override
    public void request(RequestModel request) {

        ResponseModel response = new ResponseModel();
        ConfigReader config = (ConfigReader) request.get(RequestField.CONFIG);

        // check that a valid id is given
        if (((String) request.get(RequestField.ID)).length() != 0){
            int id = (int) request.get(RequestField.ID);
            String title = (String) request.get(RequestField.TITLE);
            ArrayList<String> topics = new ArrayList<>();
            topics.add((String) request.get(RequestField.TOPIC));
            String location = (String) request.get(RequestField.LOCATION);
            int locationRadius = (int) request.get(RequestField.LOCATION_RADIUS);
            int minRating = (int) request.get(RequestField.MIN_RATING);
            int maxSize = (int) request.get(RequestField.MAX_SIZE);
            String closingTime = (String) request.get(RequestField.CLOSING_TIME);
            User creator = (User) request.get(RequestField.USER);

            this.conversation = new Conversation(id, title, topics, location,
                    locationRadius, minRating, maxSize, closingTime, creator);
            DataBase.addConversation(this.conversation);

            response.fill(ResponseField.SUCCESS, "Conversation with ID " + id + config.get("created"));
        } else {
            response.fill(ResponseField.FAILURE, config.get("invalidConversationID"));
        }
    }

    /**
     * Getter method for CreateConversation's conversation.
     * @return Returns CreateConversation's conversation.
     */
    public Conversation getConversation(){
        return this.conversation;
    }
}