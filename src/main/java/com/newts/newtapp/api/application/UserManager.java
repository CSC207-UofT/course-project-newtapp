package com.newts.newtapp.api.application;
import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.errors.InvalidPassword;
import com.newts.newtapp.api.errors.SameUser;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.entities.User;

import com.newts.newtapp.api.application.user.*;

/**
 * An object representing an UserManager of the application.
 */
public class UserManager {
    /**
     * The User this UserManager is managing.
     */
    private User user;
    private final UserRepository repository;

    /**
     * Initialize a new UserManager with given UserRepository.
     * @param repository    UserRepository to access User data
     */
    public UserManager(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a new user according to the given RequestModel and sets this UserManager's user accordingly.
     * @param request   RequestModel containing new User information.
     */
    public void createUser(RequestModel request) throws InvalidPassword {
        CreateUser createUser = new CreateUser(repository);
        createUser.request(request);
    }

    /**
     * Logs in a user according to RequestModel information and sets this UserManager's user accordingly.
     */
    public void login(RequestModel request) throws UserNotFound, InvalidPassword {
        LoginUser loginUser = new LoginUser(repository);
        loginUser.request(request);
    }

    public void logout() {
        user.logOut();
    }

    /**
     * Deletes a user according to the given RequestModel and sets this UserManager's user to null.
     * @param request   RequestModel containing delete User information.
     */
    public void deleteUser(RequestModel request) throws UserNotFound {
        DeleteUser deleteUser = new DeleteUser(repository);
        deleteUser.request(request);
    }

    /**
     * Adjust two given users such that the first follows the second.
     * @param request   RequestModel containing addFriend User information.
     */
    public void addFollow(RequestModel request) throws UserNotFound, SameUser {
        AddFollow addFollow = new AddFollow(repository);
        addFollow.request(request);
    }

    /**
     * @return  The username of the user associated with this UserManager, if said user exists (null otherwise).
     */
    public String getUsername() {
        if (user == null) {
            return null;
        }
        return user.getUsername();
    }
}
