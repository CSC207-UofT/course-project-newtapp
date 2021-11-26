package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.gateways.ConversationRepository;
import com.newts.newtapp.api.gateways.UserRepository;
import com.newts.newtapp.api.application.RequestField;
import com.newts.newtapp.api.application.RequestModel;
import com.newts.newtapp.api.application.sorters.InterestSorter;
import com.newts.newtapp.api.application.ConversationQueue;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.User;

public class GetRelevantConversations extends UserInteractor<Conversation[],UserNotFound>  {

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
        int userId = (int) request.get(RequestField.USER_ID);
        User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);

        InterestSorter sorter = new InterestSorter();

        ConversationQueue conversationQueue = new ConversationQueue(sorter, user.getLocation(),
                (int) request.get(RequestField.LOCATION_RADIUS), user.getInterests());

        conversationQueue.addAll(conversationRepository.findAll());

        return conversationQueue.toArray();
    }
}
