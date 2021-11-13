package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.*;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.entities.User;


public class Logout extends UserInteractor<Void,Exception> {
    private UserRepository repository;

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
    public Void request(RequestModel request) throws UserNotFound {
        int userId = (int) request.get(RequestField.USER_ID);

        User user = repository.findById(userId).orElseThrow(UserNotFound::new);

        user.logOut();
        repository.save(user);
        return null;
    }
}
