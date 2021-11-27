package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.application.*;
import com.newts.newtapp.api.errors.*;
import com.newts.newtapp.entities.User;
import com.newts.newtapp.api.gateways.UserRepository;

import java.util.ArrayList;

/**
 * UserInteractor that edits user information.
 * RequestModel must provide a User ids, the first is the user who wants to follow the other.
 */
public class Edit extends UserInteractor<Void, Exception> {

    /**
     * Initialize a new Edit interactor with given UserRepository.
     * @param repository    UserRepository to access user data by
     */
    public Edit(UserRepository repository) {
        super(repository);
    }

    /**
     * Accepts a Edit request.
     * @param request   a request stored as a RequestModel
     */
    @Override
    public Void request(RequestModel request) throws UserNotFound, InvalidUsername, InvalidPassword, AlreadyFollowingUser {
        int userId = (int) request.get(RequestField.USER_ID);

        // look up the user, if it doesn't exist throw UserNotFound
        User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);

        String userKey = (String) request.get(RequestField.USER_KEY);

        if (userKey.equals("editUsername")) {
            String username = (String) request.get(RequestField.USER_VALUE);
            // check if the requested username is valid
            if (username.contains(" ")) {
                throw new InvalidUsername();
            }
            user.setUsername(username);
        } else if (userKey.equals("editPassword")) {
            String password = (String) request.get(RequestField.USER_VALUE);
            // check if the requested password is valid
            if (password.length() < 6) {
                throw new InvalidPassword();
            }
            // hash the provided password with a generated salt
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            user.setPassword(hashedPassword);
        } else if (userKey.equals("addInterest")) {
            ArrayList<String> interests = user.getInterests();
            String interest = (String) request.get(RequestField.USER_VALUE);
            interests.add(interest);
        } else if (userKey.equals("deleteInterest")) {
            ArrayList<String> interests = user.getInterests();
            String interest = (String) request.get(RequestField.USER_VALUE);
            interests.remove(interest);
        } else if (userKey.equals("editLocation")) {
            String location = (String) request.get(RequestField.USER_VALUE);
            user.setLocation(location);
        }
        userRepository.save(user);
        return null;
    }
}