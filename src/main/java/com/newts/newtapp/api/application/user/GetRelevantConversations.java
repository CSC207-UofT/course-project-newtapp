package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.ConversationRepository;
import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.RequestField;
import com.newts.newtapp.api.application.RequestModel;
import com.newts.newtapp.api.application.sorters.InterestSorter;
import com.newts.newtapp.api.application.ConversationQueue;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.User;


import java.util.ArrayList;

public class GetRelevantConversations extends UserInteractor<Conversation[],UserNotFound>  {
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
    public Conversation[] request(RequestModel request) throws UserNotFound {
        int userId = (int) request.get(RequestField.ID);

        User user = repository.findById(userId).orElseThrow(UserNotFound::new);

        InterestSorter sorter = new InterestSorter();

        ArrayList<String> userInterests = user.getInterests();
        String userLocation = user.getLocation();
        int userRadius = (int) request.get(RequestField.LOCATION_RADIUS);

        ConversationQueue conversationQueue = new ConversationQueue(sorter, userLocation, userRadius, userInterests);

        conversationQueue.addAll(conversationRepository.findAll());

        return conversationQueue.toArray();

    }
}
