package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.*;
import com.newts.newtapp.api.errors.InvalidPassword;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.entities.User;
import java.util.ArrayList;


public class LoginUser extends UserInteractor<Void,Exception> {
    private UserRepository repository;

    /**
     * Initialize a new AddFollow interactor with given UserRepository.
     *
     * @param repository UserRepository to access user data by
     */
    public LoginUser(UserRepository repository) {
        super(repository);
    }

    /**
     * Accepts a CreateUserRequest
     *
     * @param request a request stored as a RequestModel
     */
    @Override
    public Void request(RequestModel request) throws UserNotFound, InvalidPassword {
        int userId = (int) request.get(RequestField.ID);

        User user = repository.findById(userId).orElseThrow(UserNotFound::new);

        if (user.getPassword() == request.get(RequestField.PASSWORD)) {
            user.logIn();
            repository.save(user);
            return null;

        } else {
            throw new InvalidPassword();
        }
    }
}
