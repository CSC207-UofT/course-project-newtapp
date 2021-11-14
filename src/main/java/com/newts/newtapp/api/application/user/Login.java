package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.*;
import com.newts.newtapp.api.errors.IncorrectPassword;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.entities.User;


public class Login extends UserInteractor<Void,Exception> {

    /**
     * Initialize a new Login interactor with given UserRepository.
     * @param repository UserRepository to access user data by
     */
    public Login(UserRepository repository) {
        super(repository);
    }

    /**
     * Accepts a Login request.
     * @param request a request stored as a RequestModel
     */
    @Override
    public Void request(RequestModel request) throws UserNotFound, IncorrectPassword {
        int userId = (int) request.get(RequestField.USER_ID);

        User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);

        if (user.getPassword() != request.get(RequestField.PASSWORD)) {
            throw new IncorrectPassword();
        }

        user.logIn();
        userRepository.save(user);
        return null;
    }
}
