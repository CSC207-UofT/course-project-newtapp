package com.newts.newtapp.api.controllers;

import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.application.UserManager;
import com.newts.newtapp.api.application.UserProfile;
import com.newts.newtapp.api.controllers.assemblers.UserProfileModelAssembler;
import com.newts.newtapp.api.controllers.forms.UserAuthForm;
import com.newts.newtapp.api.errors.*;
import com.newts.newtapp.api.controllers.forms.CreateUserForm;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


/**
 * This Controller handles User related mappings for our API.
 */
@CrossOrigin    // CORS config may need to be adjusted later depending on our needs.
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
     * @param username          Username of User
     * @return                  EntityModel containing User data
     * @throws UserNotFound     If no user exists with id
     */
    @GetMapping("/api/users/{username}")
    public EntityModel<UserProfile> getByUsername(@PathVariable String username) throws UserNotFound {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USERNAME, username);
        UserProfile profile = userManager.getProfileByUsername(request);
        return profileAssembler.toModel(profile);
    }

    /**
     * Returns a UserProfile for the user with given id.
     * @param id                id of User
     * @return                  EntityModel containing User data
     * @throws UserNotFound     If no user exists with id
     */
    @GetMapping("/api/users/id/{id}")
    public EntityModel<UserProfile> getByID(@PathVariable String id) throws UserNotFound {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USER_ID, id);
        UserProfile profile = userManager.getProfileById(request);
        return profileAssembler.toModel(profile);
    }

    /**
     * Create a new user.
     * @param form                  A filled in CreateUserForm
     * @throws UserAlreadyExists    If a user with the provided username already exists
     * @throws InvalidPassword      If the provided password is invalid
     * @throws UserNotFound         If no user exists with id
     * @throws InvalidUsername      If the provided username is not valid
     */
    @PostMapping("/api/users")
    ResponseEntity<?> create(@RequestBody CreateUserForm form) throws UserAlreadyExists, InvalidPassword, UserNotFound, InvalidUsername {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USERNAME, form.getUsername());
        request.fill(RequestField.PASSWORD, form.getPassword());
        request.fill(RequestField.INTERESTS, form.getInterests());
        userManager.create(request);
        // Build response
        EntityModel<UserProfile> profileModel = profileAssembler.toModel(userManager.getProfileByUsername(request));
        return ResponseEntity.created(profileModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(profileModel);
    }

    /**
     * Edit a user.
     * @param form                  A filled in CreateUserForm
     * @throws UserAlreadyExists    If a user with the provided username already exists
     * @throws InvalidPassword      If the provided password is invalid
     * @throws UserNotFound         If no user exists with id
     * @throws InvalidUsername      If the provided username is not valid
     */
    @PostMapping("/api/users/edit")
    ResponseEntity<?> edit(@RequestBody CreateUserForm form) throws UserAlreadyExists, InvalidPassword, UserNotFound, InvalidUsername {
        // fetch the currently authenticated user's username
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        RequestModel request = new RequestModel();
        request.fill(RequestField.USERNAME, username);
        request.fill(RequestField.NEW_USERNAME, form.getUsername());
        request.fill(RequestField.LOCATION, form.getLocation());
        request.fill(RequestField.INTERESTS, form.getInterests());
        userManager.create(request);
        // Build response
        EntityModel<UserProfile> profileModel = profileAssembler.toModel(userManager.getProfileByUsername(request));
        return ResponseEntity.created(profileModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(profileModel);
    }

    /**
     * Delete the account of the currently authenticated user, provided the given password is correct for said user.
     * We require the user to resubmit their password here for security reasons.
     * @param password              user's password
     * @throws UserNotFound         if no such user exists with given id
     * @throws IncorrectPassword    if given password is incorrect
     */
    @DeleteMapping("/api/users")
    ResponseEntity<?> delete(@RequestBody String password) throws UserNotFound, IncorrectPassword,
            ConversationNotFound {
        // fetch the currently authenticated user's username
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        RequestModel request = new RequestModel();
        request.fill(RequestField.USERNAME, username);
        request.fill(RequestField.PASSWORD, password);
        userManager.delete(request);
        return ResponseEntity.noContent().build();
    }

// These methods need to be remade in a secure and RESTful manner
    /**
     * Have a user follow another.
     * @param id1               user with id1 wants to follow id2
     * @param id2               user with id2 will be followed by id1
     * @throws UserNotFound     if no such user exists with id1 or id2
     * @throws SameUser         if id1 == id2
     */
    @PutMapping("/api/users/follow/{id1}/{id2}")
    void follow(@PathVariable int id1, @PathVariable int id2) throws UserNotFound, SameUser, AlreadyFollowingUser {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USER_ID, id1);
        request.fill(RequestField.USER_ID_TWO, id2);
        userManager.follow(request);
    }

    @GetMapping("/api/users/getRelevantConversations")
    Conversation[] getRelevantConversations(@RequestParam int userId, @RequestParam int locationRadius)
            throws UserNotFound {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USER_ID, userId);
        request.fill(RequestField.LOCATION_RADIUS, locationRadius);
        return userManager.getRelevantConversations(request);
    }
}
