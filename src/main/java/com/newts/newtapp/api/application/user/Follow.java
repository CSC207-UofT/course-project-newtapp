package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.application.*;
import com.newts.newtapp.api.errors.SameUser;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.entities.User;
import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.RequestField;

/**
 * UserInteractor that adds a follow relationship.
 * RequestModel must provide two User ids, the first is the user who wants to follow the other.
 */
public class Follow extends UserInteractor<Void, Exception> {

    /**
     * Initialize a new Follow interactor with given UserRepository.
     * @param repository    UserRepository to access user data by
     */
    public Follow(UserRepository repository) {
        super(repository);
    }

    /**
     * Accepts an Follow request.
     * @param request   a request stored as a RequestModel
     */
    @Override
    public Void request(RequestModel request) throws UserNotFound, SameUser {
        int userId = (int) request.get(RequestField.USER_ID);
        int otherId = (int) request.get(RequestField.USER_ID_TWO);

        // Check if users are the same
        if (userId == otherId) {
            throw new SameUser();
        }

        // look up the users, if they don't exist throw UserNotFound
        User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);
        User other = userRepository.findById(otherId).orElseThrow(UserNotFound::new);

        // Add userTwo to following of user
        user.addFollowing(other);
        // Add user to followers of userTwo
        other.addFollower(user);

        // Save both users
        userRepository.save(user);
        userRepository.save(other);
        return null;
    }
}