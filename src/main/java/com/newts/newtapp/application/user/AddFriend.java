package com.newts.newtapp.application.user;

import com.newts.newtapp.application.*;
import com.newts.newtapp.entities.User;

/**
 * UserInteractor for adding a friend.
 */
public class AddFriend extends UserInteractor {
    private User user;
    private User userTwo;

    /**
     * Accepts a request.
     * @param request   a request stored as a RequestModel
     */
    @Override
    public void request(RequestModel request) {
        ResponseModel response = new ResponseModel();
        ConfigReader config = (ConfigReader) request.get(RequestField.CONFIG);

        user = DataBase.getUser((String) request.get(RequestField.USERNAME));
        userTwo = DataBase.getUser((String) request.get(RequestField.USERNAME_TWO));

        if (user.getUsername() == null || userTwo.getUsername() == null) {
            // Output an error because there is no such user with the given username
            response.fill(ResponseField.FAILURE, new Exception(ApplicationExceptions.NO_SUCH_USER_ERROR));
        } else if (user.getUsername().equals(userTwo.getUsername())) {
            // Output an error because the usernames are same
            response.fill(ResponseField.FAILURE, new Exception(ApplicationExceptions.SAME_USER_NAME_ERROR));
        } else {
            // A User with the given username was found
            response.fill(ResponseField.SUCCESS, user.getUsername() + config.get("friendAdded"));
            user.addFollowing(userTwo);
            // TODO: adjust for new following/follower model
        }
        // send response through provided output boundary
        request.getOutput().respond(response);
    }

    /**
     * Returns the friend that has been found by this interactor, or null otherwise.
     * @return  the searched User or null if no user has been logged in.
     */
    public User getUser() {
        return user;
    }

    /**
     * Returns the friend that has been found by this interactor, or null otherwise.
     * @return  the searched User or null if no user has been logged in.
     */
    public User getUserTwo() {
        return userTwo;
    }
}