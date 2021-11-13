package com.newts.newtapp.api.application;
import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.errors.*;
import com.newts.newtapp.api.application.user.*;

/**
 * An object representing an UserManager of the application.
 */
public class UserManager {
    /**
     * The UserRepository this UserManager is working with.
     */
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
    public void createUser(RequestModel request) throws InvalidPassword, UserAlreadyExists {
        Create create = new Create(repository);
        create.request(request);
    }

    /**
     * Logs in a user according to RequestModel information and sets this UserManager's user accordingly.
     */
    public void login(RequestModel request) throws UserNotFound, IncorrectPassword {
        Login login = new Login(repository);
        login.request(request);
    }

    /**
     * Logs out a user.
     * @param request           RequestModel containing User's id
     * @throws UserNotFound     If user does not exist
     */
    public void logout(RequestModel request) throws UserNotFound {
        Logout logout = new Logout(repository);
        logout.request(request);
    }

    /**
     * Deletes a user according to the given RequestModel and sets this UserManager's user to null.
     * @param request   RequestModel containing delete User information.
     */
    public void deleteUser(RequestModel request) throws UserNotFound, IncorrectPassword {
        Delete delete = new Delete(repository);
        delete.request(request);
    }

    /**
     * Adjust two given users such that the first follows the second.
     * @param request   RequestModel containing addFriend User information.
     */
    public void addFollow(RequestModel request) throws UserNotFound, SameUser {
        AddFollow addFollow = new AddFollow(repository);
        addFollow.request(request);
    }
}
