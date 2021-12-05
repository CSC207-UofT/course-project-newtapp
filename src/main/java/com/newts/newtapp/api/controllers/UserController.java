package com.newts.newtapp.api.controllers;

import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.application.UserManager;
import com.newts.newtapp.api.application.datatransfer.ConversationProfile;
import com.newts.newtapp.api.application.datatransfer.UserProfile;
import com.newts.newtapp.api.controllers.assemblers.ConversationDataModelAssembler;
import com.newts.newtapp.api.controllers.assemblers.ConversationProfileModelAssembler;
import com.newts.newtapp.api.controllers.assemblers.UserProfileModelAssembler;
import com.newts.newtapp.api.controllers.forms.ChangePasswordForm;
import com.newts.newtapp.api.errors.*;
import com.newts.newtapp.api.controllers.forms.CreateUserForm;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


/**
 * This Controller handles User related mappings for our API.
 */
@CrossOrigin    // CORS config may need to be adjusted later depending on our needs.
@RestController
public class UserController {
    private final UserManager userManager;
    private final UserProfileModelAssembler profileAssembler;
    private final ConversationProfileModelAssembler conversationAssembler;

    public UserController(UserManager userManager, UserProfileModelAssembler profileAssembler, ConversationProfileModelAssembler conversationAssembler) {
        this.userManager = userManager;
        this.profileAssembler = profileAssembler;
        this.conversationAssembler = conversationAssembler;
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
    public EntityModel<UserProfile> getById(@PathVariable int id) throws UserNotFound {
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
    public EntityModel<UserProfile> edit(@RequestBody CreateUserForm form) throws UserAlreadyExists, InvalidPassword, UserNotFound, InvalidUsername {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USERNAME, returnUsername());
        request.fill(RequestField.NEW_USERNAME, form.getUsername());
        request.fill(RequestField.LOCATION, form.getLocation());
        request.fill(RequestField.INTERESTS, form.getInterests());
        userManager.edit(request);
        request.fill(RequestField.USERNAME, form.getUsername());
        // Build response
        UserProfile profile = userManager.getProfileByUsername(request);
        return profileAssembler.toModel(profile);
    }

    /**
     * Change the password of the currently authenticated user, provided the given current password is correct for said user.
     * We require the user to resubmit their password here for security reasons.
     * @param form                  form containing old and new passwords
     * @throws UserNotFound         if no such user exists with given id
     * @throws IncorrectPassword    if given password is incorrect
     */
    @PostMapping("/api/users/edit/password")
    public EntityModel<UserProfile> changePassword(@RequestBody ChangePasswordForm form) throws UserNotFound,
            IncorrectPassword, InvalidPassword {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USERNAME, returnUsername());
        request.fill(RequestField.PASSWORD, form.getPassword());
        request.fill(RequestField.NEW_PASSWORD, form.getNewPassword());
        userManager.changePassword(request);
        // Build response
        UserProfile profile = userManager.getProfileByUsername(request);
        return profileAssembler.toModel(profile);
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
        RequestModel request = new RequestModel();
        request.fill(RequestField.USERNAME, returnUsername());
        request.fill(RequestField.PASSWORD, password);
        userManager.delete(request);
        return ResponseEntity.noContent().build();
    }

    /**
     * Have a user follow another.
     * @param username               username of the user to follow
     * @throws UserNotFound          if no such user exists with id1 or id2
     * @throws SameUser              if id1 == id2
     * @throws AlreadyFollowingUser  if user follows other user already
     */
    @PostMapping("/api/users/{username}/follow")
    public EntityModel<UserProfile> follow(@PathVariable String username) throws UserNotFound, SameUser,
            AlreadyFollowingUser, UserBlocked, BlockedByUser {
        // fetch the currently authenticated user's username
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String usernameFollowing = userDetails.getUsername();
        RequestModel request = new RequestModel();
        request.fill(RequestField.USERNAME, returnUsername());
        request.fill(RequestField.USERNAME_TWO, username);
        userManager.follow(request);
        // Build response
        UserProfile profile = userManager.getProfileByUsername(request);
        return profileAssembler.toModel(profile);
    }

    /**
     * Have a user unfollow another.
     * @param  username              username of the user to unfollow
     * @throws UserNotFound          if no such user exists with id1 or id2
     * @throws SameUser              if id1 == id2
     */
    @PostMapping("/api/users/{username}/unfollow")
    public EntityModel<UserProfile> unfollow(@PathVariable String username) throws UserNotFound, SameUser {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USERNAME, returnUsername());
        request.fill(RequestField.USERNAME_TWO, username);
        userManager.unfollow(request);
        // Build response
        UserProfile profile = userManager.getProfileByUsername(request);
        return profileAssembler.toModel(profile);
    }

    // TODO implement followingConversation and getRelevantConversations
    /**
     * Return a list of conversations in which users' are user is following are
     */
    @GetMapping("/api/following/conversations")
    void followingConversation() {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USERNAME, returnUsername());
        userManager.followingConversations(request);
    }

    /**
     * Have a user follow another.
     * @param username               username of the user to block
     * @throws UserNotFound          if no such user exists with id1 or id2
     * @throws UserAlreadyBlocked    if the user is already blocked
     */
    @PostMapping("/api/users/{username}/block")
    EntityModel<UserProfile> block(@PathVariable String username) throws UserNotFound, UserAlreadyBlocked  {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USERNAME, returnUsername());
        request.fill(RequestField.USERNAME_TWO, username);
        userManager.block(request);
        // Build response
        UserProfile profile = userManager.getProfileById(request);
        return profileAssembler.toModel(profile);
    }

    @PostMapping("/api/users/{username}/rate")
    ResponseEntity<?> rate(@RequestBody int rating, @PathVariable String username) throws UserNotFound, UserAlreadyRated {
        // fetch the currently authenticated user's username
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String usernameRating = userDetails.getUsername();
        RequestModel request = new RequestModel();
        request.fill(RequestField.USERNAME, usernameRating);
        request.fill(RequestField.USERNAME_TWO, username);
        request.fill(RequestField.RATING, rating);
        userManager.rate(request);
        // return empty ResponseEntity
        return ResponseEntity.noContent().build();
    }
  
    /**
     * A helper method that returns the username of the currently authenticated user
     * @return                  Currently authenticated user's username
     */
    private String returnUsername(){
        // fetch the currently authenticated user's username
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    /**
     * Get an Arraylist of EntityModels of ConversationProfile of the conversations of the given user
     * @param username              name of the user
     * @throws UserNotFound         if user with given id doesn't exist
     * @throws ConversationNotFound if conversation with given id doesn't exist
     * @return                      Currently authenticated user's username
     */
    @GetMapping("/api/users/{username}/conversations")
    ArrayList<EntityModel<ConversationProfile>> getConversationsByUsername(@PathVariable String username)
            throws UserNotFound, ConversationNotFound {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USERNAME, username);
        ArrayList<ConversationProfile> conversations = userManager.getConversationsByUsername(request);
        ArrayList<EntityModel<ConversationProfile>> conversationsModel = new ArrayList<>();
        for (ConversationProfile c : conversations) {
            conversationsModel.add(conversationAssembler.toModel(c));
        }
        return conversationsModel;
    }
}