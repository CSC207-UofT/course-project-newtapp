package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.*;
import com.newts.newtapp.api.errors.InvalidPassword;
import com.newts.newtapp.entities.User;
import java.util.ArrayList;


public class CreateUser extends UserInteractor<Void,Exception> {
    private UserRepository repository;

    /**
     * Initialize a new AddFollow interactor with given UserRepository.
     * @param repository    UserRepository to access user data by
     */
    public CreateUser(UserRepository repository) { super(repository); }

    /**
     * Accepts a CreateUserRequest
     * @param request   a request stored as a RequestModel
     */
    @Override
    public Void request(RequestModel request) throws InvalidPassword {
        if (((String) request.get(RequestField.PASSWORD)).length() >= 6) {

            String username = ((String) request.get(RequestField.USERNAME));
            String password = (String) request.get(RequestField.PASSWORD);
            ArrayList<String> interests = new ArrayList<>();
            interests.add((String) request.get(RequestField.INTEREST));
            int id = (int) request.get(RequestField.ID);
            User user = new User(id, username, password, interests);
            repository.save(user);
            return null;
        }
        else {
            throw new InvalidPassword();
        }
    }
}

