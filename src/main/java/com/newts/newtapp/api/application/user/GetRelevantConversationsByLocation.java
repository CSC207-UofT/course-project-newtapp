package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.application.ConversationQueue;
import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.application.datatransfer.ConversationProfile;
import com.newts.newtapp.api.application.sorters.InterestSorter;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.api.gateways.ConversationRepository;
import com.newts.newtapp.api.gateways.UserRepository;
import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.User;

import java.util.ArrayList;
import java.util.List;

public class GetRelevantConversationsByLocation extends UserInteractor<ConversationProfile[], UserNotFound>  {
    /**
     * Initialize a new Create interactor with given UserRepository.
     * @param repository    UserRepository to access user data by
     */
    public GetRelevantConversationsByLocation(UserRepository repository, ConversationRepository conversationRepository) {
        super(repository, conversationRepository);
    }

    /**
     * Completes a GetRelevantConversationsByLocation request.
     * Looks for conversations matching a user's location.
     * @param request   a request stored as a RequestModel
     * @return ArrayList of Conversations matching a user's conversation.
     * @throws UserNotFound if the user in the request can not be found.
     */
    public ConversationProfile[] request(RequestModel request) throws UserNotFound {
        int userId = (int) request.get(RequestField.USER_ID);
        User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);

        InterestSorter sorter = new InterestSorter();

        ConversationQueue conversationQueue = new ConversationQueue(sorter, user.getLocation(),
                user.getInterests());

        ArrayList<Conversation> matchingConversations = new ArrayList<>();
        List<Conversation> conversationList = this.conversationRepository.findAll();

        String userLocation = user.getLocation();

        for (Conversation conversation : conversationList) {
            if (conversation.getLocation().equals(userLocation)) {
                matchingConversations.add(conversation);
            }
        }

        conversationQueue.addAll(matchingConversations);

        ArrayList<ConversationProfile> filteredConversationsProfile = new ArrayList<>();

        // Removing any conversations authored by users on the user's blocked list
        for(Conversation c:conversationQueue.toArray()){
            if(!user.getBlockedUsers().contains(c.getAuthorID())){
                ConversationProfile cp = new ConversationProfile(c);
                filteredConversationsProfile.add(cp);
            }
        }
        return filteredConversationsProfile.toArray(ConversationProfile[]::new);
    }
}
