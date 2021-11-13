package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.ConversationRepository;
import com.newts.newtapp.api.MessageRepository;
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
    private MessageRepository messageRepository;

    /**
     * Initialize a new Create interactor with given UserRepository.
     * @param repository    UserRepository to access user data by
     */
    public GetRelevantConversations(UserRepository repository, ConversationRepository conversationRepository) {
        super(repository, conversationRepository);
    }

    /**
     *
     * @param request   Accepts a GetRelevantConversations Request
     * @return An array of Conversations
     * @throws UserNotFound if the user can not be found
     */
    @Override
    public Conversation[] request(RequestModel request) throws UserNotFound {
        int userId = (int) request.get(RequestField.ID);
        User user = repository.findById(userId).orElseThrow(UserNotFound::new);

        InterestSorter sorter = new InterestSorter();

        ConversationQueue conversationQueue = new ConversationQueue(sorter, user.getLocation(),
                (int) request.get(RequestField.LOCATION_RADIUS), user.getInterests());

        conversationQueue.addAll(conversationRepository.findAll());

        return conversationQueue.toArray();

    }
}
