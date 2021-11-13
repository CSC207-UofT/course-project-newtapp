package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.ConversationRepository;
import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.RequestField;
import com.newts.newtapp.api.application.RequestModel;
import com.newts.newtapp.api.application.sorters.InterestSorter;
import com.newts.newtapp.api.application.user.UserInteractor;
import com.newts.newtapp.api.application.ConversationQueue;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.User;


import java.util.ArrayList;

public class GetRelevantConversations extends UserInteractor<ArrayList<Conversation>,Exception>  {
    private UserRepository repository;
    private ConversationRepository conversationRepository;

    /**
     * Initialize a new Create interactor with given UserRepository.
     * @param repository    UserRepository to access user data by
     */
    public GetRelevantConversations(UserRepository repository, ConversationRepository conversationRepository) {
        super(repository, conversationRepository);
    }

    @Override
    public ArrayList<Conversation> request(RequestModel request) throws Exception {
        int userId = (int) request.get(RequestField.ID);

        User user = repository.findById(userId).orElseThrow(UserNotFound::new);

        InterestSorter sorter = new InterestSorter();





        return null;
    }
}

//
//    @Override
//    public void request(RequestModel request) {
//        // Fetching User to generate ConversationQueue for
//        user = DataBase.getUser((String) request.get(RequestField.USERNAME));
//
//        // Create a ConversationQueue with desired settings
//        InterestSorter sorter = new InterestSorter();
//        ArrayList<String> interests = new ArrayList<>();
//        interests.add((String) request.get(RequestField.INTEREST));
//        conversationQueue = new ConversationQueue(sorter, (String) request.get(RequestField.LOCATION),
//                (int) request.get(RequestField.LOCATION_RADIUS),
//                interests);
//        ResponseModel response = new ResponseModel();
//
//        //Get all the conversations in DataBase
//        conversationQueue.addAll(DataBase.getConversationList());
//        response.fill(ResponseField.VALUE, conversationQueue.toArray());
//        // send response through provided output boundary
//        request.getOutput().respond(response);
//    }
//    /**
//     * Getter method for GetRelevantConversation's conversationQueue.
//     * @return Returns GetRelevantConversation's conversationQueue.
//     */
//    public ConversationQueue getConversationQueue(){
//
//        return this.conversationQueue;
//    }
//
//    /**
//     * Getter method for GetRelevantConversation's user.
//     * @return Returns GetRelevantConversation's user.
//     */
//    public User getUser(){
//
//        return this.user;
//    }