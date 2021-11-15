package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.*;
import com.newts.newtapp.api.errors.ConversationNotFound;
import com.newts.newtapp.api.errors.IncorrectPassword;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.User;

public class Delete extends UserInteractor<Void,Exception> {

    /**
     * Initialize a new Follow interactor with given UserRepository.
     * @param repository    UserRepository to access user data by
     */
    public Delete(UserRepository repository) { super(repository); }

    /**
     * Accepts a CreateUserRequest
     * @param request   a request stored as a RequestModel
     */
    @Override
    public Void request(RequestModel request) throws UserNotFound, IncorrectPassword, ConversationNotFound {
        int userId = (int) request.get(RequestField.USER_ID);
        String password = (String) request.get(RequestField.PASSWORD);
        User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);

        if (!(user.getPassword().equals(password))) {
            throw new IncorrectPassword();
        }

        // Loops through the "to-be-deleted" user's follower list and  makes each user
        // on the list unfollow them.
        for(int follower:user.getFollowers()){
            User user2 = userRepository.findById(follower).orElseThrow(UserNotFound::new);
            user2.removeFollowing(user);
            userRepository.save(user2);
        }

        // Loops through user's followed list and unfollows each user
        for(int following:user.getFollowing()){
            User user2 = userRepository.findById(following).orElseThrow(UserNotFound::new);
            user2.removeFollower(user);
            userRepository.save(user2);
        }

        // Loops through list of conversations that user is in and removes them
        for(int conversationID:user.getConversations()){
            Conversation conversation = conversationRepository.findById(conversationID).orElseThrow(ConversationNotFound::new);
            if (conversation.getNumUsers() == 1){
                conversationRepository.delete(conversation);
            } else{
                conversation.removeUser(user);
                conversationRepository.save(conversation);
            }
        }

        userRepository.delete(user);
        return null;
       }
}