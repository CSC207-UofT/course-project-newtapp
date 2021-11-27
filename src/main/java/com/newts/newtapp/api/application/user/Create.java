package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.gateways.UserRepository;
import com.newts.newtapp.api.application.*;
import com.newts.newtapp.api.errors.InvalidPassword;
import com.newts.newtapp.api.errors.UserAlreadyExists;
import com.newts.newtapp.entities.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

public class Create extends UserInteractor<Void,Exception> {

    /**
     * Initialize a new Create interactor with given UserRepository.
     * @param repository    UserRepository to access user data by
     */
    public Create(UserRepository repository) {
        super(repository);
    }

    /**
     * Accepts a Create request.
     * @param request   a request stored as a RequestModel
     */
    @Override
    public Void request(RequestModel request) throws InvalidPassword, UserAlreadyExists {
        String username = (String) request.get(RequestField.USERNAME);
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UserAlreadyExists();
        }
        // Check password is valid
        String password = (String) request.get(RequestField.PASSWORD);
        if (((String) request.get(RequestField.PASSWORD)).length() < 6) {
            throw new InvalidPassword();
        }
        // hash the provided password with a generated salt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        ArrayList<String> interests = new ArrayList<>();
        interests.add((String) request.get(RequestField.INTEREST));

        User user = new User(0, username, hashedPassword, interests);
        userRepository.save(user);
        return null;
    }
}

