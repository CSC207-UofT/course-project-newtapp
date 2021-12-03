package com.newts.newtapp.api.controllers;

import com.newts.newtapp.api.application.ConversationManager;
import com.newts.newtapp.api.application.UserManager;
import com.newts.newtapp.api.application.datatransfer.UserProfile;
import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.controllers.forms.CreateConversationForm;
import com.newts.newtapp.api.errors.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * This Controller handles Conversation related mappings for our API.
 */
@RestController
public class ConversationController {
    private final ConversationManager conversationManager;
    private final UserManager userManager;

    public ConversationController(ConversationManager conversationManager, UserManager userManager) {
        this.conversationManager = conversationManager;
        this.userManager = userManager;
    }

    /**
     * Create a new conversation.
     * @param form                        A filled in CreateConversationForm
     * @throws InvalidConversationSize    If the provided conversation size is out of range
     * @throws InvalidMinRating           If the provided minimum rating is out of range
     * @throws UserNotFound               If no user exists with id
     */
    @PostMapping("/api/conversations")
    void create(@RequestBody CreateConversationForm form) throws InvalidMinRating, InvalidConversationSize, UserNotFound {
        // fetch the currently authenticated user's username
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        // use the username to get the userId
        RequestModel request0 = new RequestModel();
        request0.fill(RequestField.USERNAME, username);
        UserProfile userProfile = userManager.getProfileByUsername(request0);
        int userId = userProfile.id;

        // use the userId and the form to create a conversation
        RequestModel request = new RequestModel();
        request.fill(RequestField.TITLE, form.getTitle());
        request.fill(RequestField.TOPICS, form.getTopics());
        request.fill(RequestField.LOCATION, form.getLocation());
        request.fill(RequestField.LOCATION_RADIUS, form.getLocationRadius());
        request.fill(RequestField.MIN_RATING, form.getMinRating());
        request.fill(RequestField.MAX_SIZE, form.getMaxSize());
        request.fill(RequestField.USER_ID, userId);
        conversationManager.createConversation(request);
        // Build response
    }

    /**
     * Join a new conversation.
     * @param id                          Conversation with id
     * @throws UserBelowMinimumRating     If the user's rating is below conversation's minimum rating
     * @throws UserNotFound               If no user exists with id
     * @throws UserBlocked                If the user is blocked from the conversation
     * @throws ConversationFull           If the conversation is full
     * @throws ConversationNotFound       If no conversation exists with id
     */
    @PostMapping("/api/conversations/{id}/join")
    void join(@PathVariable int id) throws UserBelowMinimumRating, UserNotFound, UserBlocked, ConversationFull, ConversationNotFound {
        // fetch the currently authenticated user's username
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        // use the username to get the userId
        RequestModel request0 = new RequestModel();
        request0.fill(RequestField.USERNAME, username);
        UserProfile userProfile = userManager.getProfileByUsername(request0);
        int userId = userProfile.id;

        RequestModel request = new RequestModel();
        request.fill(RequestField.CONVERSATION_ID, id);
        request.fill(RequestField.USER_ID, userId);
        conversationManager.addUser(request);
        // Build response
    }



}