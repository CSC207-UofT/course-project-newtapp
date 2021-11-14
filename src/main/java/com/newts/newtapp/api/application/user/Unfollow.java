package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.RequestField;
import com.newts.newtapp.api.application.RequestModel;
import com.newts.newtapp.api.errors.SameUser;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.entities.User;

public class Unfollow extends UserInteractor<Void, Exception>{
    private UserRepository userRepository;

    /**
     * UserInteractor which removes follower/following relationship between users
     * @param userRepository UserRepository which stores user data
     */
    public Unfollow(UserRepository userRepository){super(userRepository);}

    /**
     * Accepts a request.
     * @param request a request stored as a RequestModel
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

        // Check if user is following other
        if (user.getFollowing().contains(otherId)){user.removeFollowing(other);}

        // Check if other has user as a follower
        if(other.getFollowers().contains(userId)){other.removeFollower(user);}

        return null;
    }
}
