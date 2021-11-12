package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.*;
import com.newts.newtapp.api.errors.IncorrectPassword;
import com.newts.newtapp.api.errors.InvalidPassword;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.entities.User;
import java.util.ArrayList;


public class LoginUser extends UserInteractor<Void,Exception> {
    private UserRepository repository;

    /**
     * Initialize a new LoginUser interactor with given UserRepository.
     * @param repository UserRepository to access user data by
     */
    public LoginUser(UserRepository repository) {
        super(repository);
    }

    /**
     * Accepts a LoginUser request.
     * @param request a request stored as a RequestModel
     */
    @Override
    public Void request(RequestModel request) throws UserNotFound, IncorrectPassword {
        int userId = (int) request.get(RequestField.ID);

        User user = repository.findById(userId).orElseThrow(UserNotFound::new);

        if (user.getPassword() != request.get(RequestField.PASSWORD)) {
            throw new IncorrectPassword();
        }

        user.logIn();
        repository.save(user);
        return null;
    }
}
