package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.*;
import com.newts.newtapp.api.errors.InvalidPassword;
import com.newts.newtapp.api.errors.UserAlreadyExists;
import com.newts.newtapp.entities.User;
import java.util.ArrayList;


public class Create extends UserInteractor<Void,Exception> {

    /**
     * Initialize a new Create interactor with given UserRepository.
     * @param repository    UserRepository to access user data by
     */
    public Create(UserRepository repository) { super(repository); }

    /**
     * Accepts a Create request.
     * @param request   a request stored as a RequestModel
     */
    @Override
    public Void request(RequestModel request) throws InvalidPassword, UserAlreadyExists {
        String username = (String) request.get(RequestField.USERNAME);
        if (!(userRepository.findByUsernameIgnoreCase(username).isEmpty())) {
            throw new UserAlreadyExists();
        }
        String password = (String) request.get(RequestField.PASSWORD);
        if (((String) request.get(RequestField.PASSWORD)).length() < 6) {
            throw new InvalidPassword();
        }
        ArrayList<String> interests = new ArrayList<>();
        interests.add((String) request.get(RequestField.INTEREST));

        int id = 0; // TODO: ensure id generation works as expected
        User user = new User(id, username, password, interests);
        userRepository.save(user);
        return null;
    }
}

