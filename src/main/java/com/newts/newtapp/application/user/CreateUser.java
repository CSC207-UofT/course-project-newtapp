package com.newts.newtapp.application.user;

import com.newts.newtapp.application.*;
import com.newts.newtapp.entities.User;
import java.util.ArrayList;


public class CreateUser extends UserInteractor {
    private User user;


    @Override
    public void request(RequestModel request) {
        ResponseModel response = new ResponseModel();
        ConfigReader config = (ConfigReader) request.get(RequestField.CONFIG);

        // check that password is strong enough before creating User
        if (((String) request.get(RequestField.PASSWORD)).length() >= 6) {

            String username = ((String) request.get(RequestField.USERNAME));
            String password = (String) request.get(RequestField.PASSWORD);
            ArrayList<String> interests = new ArrayList<>();
            interests.add((String) request.get(RequestField.INTEREST));
            int id = (int) request.get(RequestField.ID); //TODO adjust for new data access object
            User user = new User(username, password, interests, id);

            DataBase.addUser(user);
            response.fill(ResponseField.SUCCESS, user.getUsername() + config.get("created"));
        }
        else {
            response.fill(ResponseField.FAILURE, config.get("invalidPassword"));
        }

        // send response through provided output boundary
        request.getOutput().respond(response);
    }

    /**
     * Returns the User that was successfully created this interactor, or null otherwise.
     * @return created User or null if no user has been created.
     */
    public User getUser() {
        return user;
    }

}

