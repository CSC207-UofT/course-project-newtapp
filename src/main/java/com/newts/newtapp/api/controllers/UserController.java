package com.newts.newtapp.api.controllers;

import com.newts.newtapp.api.application.RequestField;
import com.newts.newtapp.api.application.RequestModel;
import com.newts.newtapp.api.application.UserManager;
import com.newts.newtapp.api.application.UserProfile;
import com.newts.newtapp.api.controllers.assemblers.UserProfileModelAssembler;
import com.newts.newtapp.api.errors.*;
import com.newts.newtapp.api.forms.CreateUserForm;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * This Controller handles User related mappings for our API.
 */
@RestController
public class UserController {
    private final UserManager userManager;
    private final UserProfileModelAssembler profileAssembler;

    public UserController(UserManager userManager, UserProfileModelAssembler profileAssembler) {
        this.userManager = userManager;
        this.profileAssembler = profileAssembler;
    }

    /**
     * Returns a UserProfile for the user with given id.
     * @param id                Id of User
     * @return                  UserProfile of User with id
     * @throws UserNotFound     If no user exists with id
     */
    @GetMapping("/api/users/{id}")
    public EntityModel<UserProfile> get(@PathVariable int id) throws UserNotFound {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USER_ID, id);
        UserProfile profile = userManager.getProfileById(request);
        return profileAssembler.toModel(profile);
    }
//// These methods need to be remade in a secure and RESTful manner
//    /**
//     * Login user with given id provided password is correct.
//     * @param username              username of user to log in
//     * @param password              password of user to log in
//     * @throws UserNotFound         if no user exists with id
//     * @throws IncorrectPassword    if password is incorrect for user with id
//     */
//    @PutMapping("/api/users/login/{username}")
//    void login(@PathVariable String username, @RequestParam String password) throws UserNotFound, IncorrectPassword {
//        RequestModel request = new RequestModel();
//        request.fill(RequestField.USERNAME, username);
//        request.fill(RequestField.PASSWORD, password);
//        userManager.login(request);
//    }
//
//    /**
//     * Logout user with given id provided password is correct.
//     * @param id                    id of user to log out
//     * @param password              password of user to log out
//     * @throws UserNotFound         if no user exists with id
//     * @throws IncorrectPassword    if password is incorrect for user with id
//     */
//    @PutMapping("/api/users/logout/{id}")
//    void logout(@PathVariable int id, @RequestParam String password) throws UserNotFound, IncorrectPassword {
//        RequestModel request = new RequestModel();
//        request.fill(RequestField.USER_ID, id);
//        request.fill(RequestField.PASSWORD, password);
//        userManager.logout(request);
//    }

    /**
     * Create a new user.
     * @param form                  A filled in CreateUserForm
     * @throws UserAlreadyExists    If a user with the provided username already exists
     * @throws InvalidPassword      If the provided password is invalid
     */
    @PostMapping("/api/users")
    ResponseEntity<?> create(@RequestBody CreateUserForm form) throws UserAlreadyExists, InvalidPassword, UserNotFound {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USERNAME, form.getUsername());
        request.fill(RequestField.PASSWORD, form.getPassword());
        request.fill(RequestField.INTEREST, form.getInterest());
        userManager.create(request);
        // Build response
        EntityModel<UserProfile> profileModel = profileAssembler.toModel(userManager.getProfileByUsername(request));
        return ResponseEntity.created(profileModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(profileModel);
    }

//    /**
//     * Delete a user with a given id, provided the given password is correct for said user.
//     * @param form                  DeleteUserForm containing user id and password
//     * @throws UserNotFound         if no such user exists with given id
//     * @throws IncorrectPassword    if given password is incorrect
//     */
//    @DeleteMapping("/api/users")
//    ResponseEntity<?> delete(@RequestBody DeleteUserForm form) throws UserNotFound, IncorrectPassword,
//            ConversationNotFound {
//        RequestModel request = new RequestModel();
//        request.fill(RequestField.USER_ID, form.getId());
//        request.fill(RequestField.PASSWORD, form.getPassword());
//        userManager.delete(request);
//        return ResponseEntity.noContent().build();
//    }

//// These methods need to be remade in a secure and RESTful manner
//    /**
//     * Have a user follow another.
//     * @param id1               user with id1 wants to follow id2
//     * @param id2               user with id2 will be followed by id1
//     * @throws UserNotFound     if no such user exists with id1 or id2
//     * @throws SameUser         if id1 == id2
//     */
//    @PutMapping("/api/users/follow/{id1}/{id2}")
//    void follow(@PathVariable int id1, @PathVariable int id2) throws UserNotFound, SameUser, AlreadyFollowingUser {
//        RequestModel request = new RequestModel();
//        request.fill(RequestField.USER_ID, id1);
//        request.fill(RequestField.USER_ID_TWO, id2);
//        userManager.follow(request);
//    }
//
//    @GetMapping("/api/users/getRelevantConversations")
//    Conversation[] getRelevantConversations(@RequestParam int userId, @RequestParam int locationRadius)
//            throws UserNotFound {
//        RequestModel request = new RequestModel();
//        request.fill(RequestField.USER_ID, userId);
//        request.fill(RequestField.LOCATION_RADIUS, locationRadius);
//        return userManager.getRelevantConversations(request);
//    }
}
