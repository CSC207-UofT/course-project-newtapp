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

public class GetRelevantConversationsByFollowing extends UserInteractor<ArrayList<ConversationProfile>, UserNotFound> {
    /**
     * Initialize a new Create interactor with given UserRepository.
     * @param repository    UserRepository to access user data by
     */
    public GetRelevantConversationsByFollowing(UserRepository repository, ConversationRepository conversationRepository) {
        super(repository, conversationRepository);
    }

    /**
     * Completes a GetRelevantConversations request.
     * Looks for relevant conversations by sorting through conversations of a user's following.
     * @param request   a request stored as a RequestModel
     * @return ArrayList of Conversations containing conversations of a user's following, as sorted by InterestSorter.
     * @throws UserNotFound if the user in the request can not be found.
     */
    @Override
    public ArrayList<ConversationProfile> request(RequestModel request) throws UserNotFound {
        int userId = (int) request.get(RequestField.USER_ID);
        User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);

        InterestSorter sorter = new InterestSorter();

        ConversationQueue conversationQueue = new ConversationQueue(sorter, user.getLocation(),
                user.getInterests());

        ArrayList<Conversation> followingConversations = new ArrayList<>();

        ArrayList<Integer> following = user.getFollowing();

        for (Conversation c : conversationRepository.findAll()) {
            for (int i : following) {
                if (c.getUsers().contains(i) && !(followingConversations.contains(c))){
                    followingConversations.add(c);
                }
            }
        }

        conversationQueue.addAll(followingConversations);

        ArrayList<ConversationProfile> filteredConversations = new ArrayList<>();

        // Removing any conversations authored by users on the user's blocked list
        for(Conversation c:conversationQueue.toArray()){
            if(!user.getBlockedUsers().contains(c.getAuthorID())){
                ConversationProfile cp = new ConversationProfile(c);
                filteredConversations.add(cp);
            }
        }
        return filteredConversations;
    }
}

