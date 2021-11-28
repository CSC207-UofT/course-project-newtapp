package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.gateways.UserRepository;
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
        String username = (String) request.get(RequestField.USERNAME);

        User user = userRepository.findByUsername(username).orElseThrow(UserNotFound::new);

        if (!(user.getPassword().equals(request.get(RequestField.PASSWORD)))) {
            throw new IncorrectPassword();
        }

        user.logIn();
        userRepository.save(user);
        return null;
    }
}
