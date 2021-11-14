package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.*;
import com.newts.newtapp.api.errors.IncorrectPassword;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.entities.User;

public class Delete extends UserInteractor<Void,Exception> {

    /**
     * Initialize a new Follow interactor with given UserRepository.
     * @param repository    UserRepository to access user data by
     */
    public Delete(UserRepository repository) { super(repository); }

    /**
     * Accepts a CreateUserRequest
     * @param request   a request stored as a RequestModel
     */
    @Override
    public Void request(RequestModel request) throws UserNotFound, IncorrectPassword {
        int userId = (int) request.get(RequestField.USER_ID);
        String password = (String) request.get(RequestField.PASSWORD);
        User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);

        if (!(user.getPassword().equals(password))) {
            throw new IncorrectPassword();
        }

        userRepository.delete(user);
        return null;
       }
}