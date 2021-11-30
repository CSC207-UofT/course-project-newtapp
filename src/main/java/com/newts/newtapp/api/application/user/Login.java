package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.gateways.UserRepository;
import com.newts.newtapp.api.application.*;
import com.newts.newtapp.api.errors.IncorrectPassword;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.entities.User;
import org.springframework.security.crypto.bcrypt.BCrypt;


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
        String password = (String) request.get(RequestField.PASSWORD);

        User user = userRepository.findByUsername(username).orElseThrow(UserNotFound::new);

        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new IncorrectPassword();
        }

        user.logIn();
        userRepository.save(user);
        return null;
    }
}
