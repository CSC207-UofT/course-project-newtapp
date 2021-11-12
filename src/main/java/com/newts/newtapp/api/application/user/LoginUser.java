package com.newts.newtapp.api.application.user;

import com.newts.newtapp.entities.User;

public class LoginUser extends UserInteractor {
    private User user;

    /**
     * Accepts a request.
     * @param request   a request stored as a RequestModel
     */
    @Override
    public void request(RequestModel request) {
        ResponseModel response = new ResponseModel();
        ConfigReader config = (ConfigReader) request.get(RequestField.CONFIG);

        user = DataBase.getUser((String) request.get(RequestField.USERNAME));

        if (user.getUsername() == null) {
            // Output an error because there is no such user with the given username
            response.fill(ResponseField.FAILURE, new Exception(ApplicationExceptions.NO_SUCH_USER_ERROR));
        } else if (user.getPassword() == request.get(RequestField.PASSWORD)) {
            // Login the user
            user.logIn();
            response.fill(ResponseField.SUCCESS, user.getUsername() + config.get("loggedIn"));
        } else {
            // Input password was incorrect.
            response.fill(ResponseField.FAILURE, config.get("incorrectPassword"));
        }
        // send response through provided output boundary
        request.getOutput().respond(response);
    }

    /**
     * Returns the User that has been logged in by this interactor, or null otherwise.
     * @return  logged in User or null if no user has been logged in.
     */
    public User getUser() {
        return user;
    }
}


