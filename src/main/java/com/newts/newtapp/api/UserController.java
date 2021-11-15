package com.newts.newtapp.api;

import com.newts.newtapp.api.application.RequestField;
import com.newts.newtapp.api.application.RequestModel;
import com.newts.newtapp.api.application.UserManager;
import com.newts.newtapp.api.application.UserProfile;
import com.newts.newtapp.api.errors.*;
import com.newts.newtapp.entities.Conversation;
import org.springframework.web.bind.annotation.*;

/**
 * This Controller handles User related mappings for our API.
 */
@RestController
public class UserController {
    private final UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Returns a UserProfile for the user with given id.
     * @param id                id of User
     * @return                  UserProfile of User with id
     * @throws UserNotFound     If no user exists with id
     */
    @GetMapping("/users")
    UserProfile getById(@RequestParam int id) throws UserNotFound {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USER_ID, id);
        return userManager.getProfileById(request);
    }

    /**
     * Returns a UserProfile for the user with given username.
     * @param username          username of User
     * @return                  UserProfile of User with username
     * @throws UserNotFound     If no user exists with username
     */
    @GetMapping("/users/{username}")
    UserProfile getByUsername(@PathVariable String username) throws UserNotFound {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USERNAME, username);
        return userManager.getProfileByUsername(request);
    }

    /**
     * Login user with given id provided password is correct.
     * @param username              username of user to log in
     * @param password              password of user to log in
     * @throws UserNotFound         if no user exists with id
     * @throws IncorrectPassword    if password is incorrect for user with id
     */
    @PutMapping("/users/login/{username}")
    void login(@PathVariable String username, @RequestParam String password) throws UserNotFound, IncorrectPassword {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USERNAME, username);
        request.fill(RequestField.PASSWORD, password);
        userManager.login(request);
    }

    /**
     * Logout user with given id provided password is correct.
     * @param id                    id of user to log out
     * @param password              password of user to log out
     * @throws UserNotFound         if no user exists with id
     * @throws IncorrectPassword    if password is incorrect for user with id
     */
    @PutMapping("/users/logout/{id}")
    void logout(@PathVariable int id, @RequestParam String password) throws UserNotFound, IncorrectPassword {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USER_ID, id);
        request.fill(RequestField.PASSWORD, password);
        userManager.logout(request);
    }

    /**
     * Create a new user
     * @param username              Username of new user
     * @param password              Password of new user
     * @param interest              One interest of new user
     * @throws UserAlreadyExists    If a user with username already exists
     * @throws InvalidPassword      If password is invalid
     */
    @PostMapping("/users")
    void create(@RequestParam String username, @RequestParam String password, @RequestParam String interest)
            throws UserAlreadyExists, InvalidPassword {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USERNAME, username);
        request.fill(RequestField.PASSWORD, password);
        request.fill(RequestField.INTEREST, interest);
        userManager.create(request);
    }

    /**
     * Delete a user with a given id, provided the given password is correct for said user.
     * @param id                    id of User to delete
     * @param password              password of user with id
     * @throws UserNotFound         if no such user exists with id
     * @throws IncorrectPassword    if password is incorrect
     */
    @DeleteMapping("/users/{id}")
    void delete(@PathVariable int id, @RequestParam String password) throws UserNotFound, IncorrectPassword, ConversationNotFound {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USER_ID, id);
        request.fill(RequestField.PASSWORD, password);
        userManager.delete(request);
    }

    /**
     * Have a user follow another.
     * @param id1               user with id1 wants to follow id2
     * @param id2               user with id2 will be followed by id1
     * @throws UserNotFound     if no such user exists with id1 or id2
     * @throws SameUser         if id1 == id2
     */

    @PutMapping("/users/follow/{id1}/{id2}")
    void follow(@PathVariable int id1, @PathVariable int id2) throws UserNotFound, SameUser, AlreadyFollowingUser {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USER_ID, id1);
        request.fill(RequestField.USER_ID_TWO, id2);
        userManager.follow(request);
    }

    @GetMapping("/users/getRelevantConversations")
    Conversation[] getRelevantConversations(@RequestParam int userId, @RequestParam int locationRadius)
            throws UserNotFound {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USER_ID, userId);
        request.fill(RequestField.LOCATION_RADIUS, locationRadius);
        return userManager.getRelevantConversations(request);
    }
}
