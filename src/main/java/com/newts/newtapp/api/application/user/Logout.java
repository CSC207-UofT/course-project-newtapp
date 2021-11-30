package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.gateways.UserRepository;
import com.newts.newtapp.api.application.*;
import com.newts.newtapp.api.errors.IncorrectPassword;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.entities.User;
import org.springframework.security.crypto.bcrypt.BCrypt;


public class Logout extends UserInteractor<Void,Exception> {

    /**
     * Initialize a new Logout interactor with given UserRepository.
     *
     * @param repository UserRepository to access user data by
     */
    public Logout(UserRepository repository) {
        super(repository);
    }

    /**
     * Accepts a Logout request.
     *
     * @param request a request stored as a RequestModel
     */
    @Override
    public Void request(RequestModel request) throws UserNotFound, IncorrectPassword {
        int userId = (int) request.get(RequestField.USER_ID);
        String password = (String) request.get(RequestField.PASSWORD);
        User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);

        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new IncorrectPassword();
        }

        user.logOut();
        userRepository.save(user);
        return null;
    }
}
