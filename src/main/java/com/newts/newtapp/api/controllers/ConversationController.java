package com.newts.newtapp.api.controllers;

import com.newts.newtapp.api.application.ConversationManager;
import com.newts.newtapp.api.application.UserManager;
import com.newts.newtapp.api.application.datatransfer.ConversationData;
import com.newts.newtapp.api.application.datatransfer.ConversationProfile;
import com.newts.newtapp.api.application.datatransfer.MessageData;
import com.newts.newtapp.api.application.datatransfer.UserProfile;
import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.controllers.assemblers.ConversationDataModelAssembler;
import com.newts.newtapp.api.controllers.assemblers.ConversationDataWithLink;
import com.newts.newtapp.api.controllers.assemblers.ConversationProfileModelAssembler;
import com.newts.newtapp.api.controllers.assemblers.MessageDataModelAssembler;
import com.newts.newtapp.api.controllers.forms.CreateConversationForm;
import com.newts.newtapp.api.errors.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * This Controller handles Conversation related mappings for our API.
 */
@CrossOrigin    // CORS config may need to be adjusted later depending on our needs.
@RestController
public class ConversationController {
    private final ConversationManager conversationManager;
    private final UserManager userManager;
    private final ConversationProfileModelAssembler profileAssembler;
    private final ConversationDataModelAssembler dataAssembler;
    private final MessageDataModelAssembler messageDataModelAssembler;

    public ConversationController(ConversationManager conversationManager, UserManager userManager,
                                  ConversationProfileModelAssembler profileAssembler,
                                  ConversationDataModelAssembler dataAssembler, MessageDataModelAssembler messageDataModelAssembler) {
        this.conversationManager = conversationManager;
        this.userManager = userManager;
        this.profileAssembler = profileAssembler;
        this.dataAssembler = dataAssembler;
        this.messageDataModelAssembler = messageDataModelAssembler;
    }

    /**
     * Returns a ConversationProfile for the Conversation with given id.
     * @param id                        id of Conversation
     * @return                          EntityModel containing Conversation data
     * @throws ConversationNotFound     If no Conversation exists with id
     */
    @GetMapping("/api/conversations/{id}")
    public EntityModel<ConversationProfile> getProfile(@PathVariable int id) throws ConversationNotFound {
        RequestModel request = new RequestModel();
        request.fill(RequestField.CONVERSATION_ID, id);
        ConversationProfile profile = conversationManager.getProfile(request);
        return profileAssembler.toModel(profile);
    }

    /**
     * Returns a ConversationData for the Conversation with given id.
     * @param id                        id of Conversation
     * @return                          EntityModel containing Conversation data
     * @throws ConversationNotFound     If no Conversation exists with id
     */
    @GetMapping("/api/conversations/{id}/view")
    public EntityModel<ConversationDataWithLink> getData(@PathVariable int id) throws ConversationNotFound, UserNotFound, MessageNotFound, IncorrectPassword {
        RequestModel request = new RequestModel();
        request.fill(RequestField.CONVERSATION_ID, id);
        ConversationData data = conversationManager.getData(request);
        return dataAssembler.toModel(data);
    }

    /**
     * A helper method that returns the ID of the currently authenticated user
     * @return                  Currently authenticated user's id
     * @throws UserNotFound     If no User exists with username
     */
    private int returnId() throws UserNotFound {
        // fetch the currently authenticated user's username
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        // use the username to get the userId
        RequestModel request = new RequestModel();
        request.fill(RequestField.USERNAME, username);
        UserProfile userProfile = userManager.getProfileByUsername(request);
        return userProfile.id;
    }

    /**
     * Create a new conversation.
     * @param form                        A filled in CreateConversationForm
     * @throws InvalidConversationSize    If the provided conversation size is out of range
     * @throws InvalidMinRating           If the provided minimum rating is out of range
     * @throws UserNotFound               If no user exists with id
     * @throws ConversationNotFound       If no conversation exists with id
     */
    @PostMapping("/api/conversations")
    ResponseEntity<?> create(@RequestBody CreateConversationForm form) throws InvalidMinRating, InvalidConversationSize,
            UserNotFound, ConversationNotFound {
        //initiate a request model requesting the body and the userId.
        RequestModel request = new RequestModel();

        //fill in the body
        request.fill(RequestField.TITLE, form.getTitle());
        request.fill(RequestField.TOPICS, form.getTopics());
        request.fill(RequestField.LOCATION, form.getLocation());
        request.fill(RequestField.LOCATION_RADIUS, form.getLocationRadius());
        request.fill(RequestField.MIN_RATING, form.getMinRating());
        request.fill(RequestField.MAX_SIZE, form.getMaxSize());
        //fill in the userId
        request.fill(RequestField.USER_ID, returnId());

        conversationManager.createConversation(request);

        // Build response
        EntityModel<ConversationProfile> profileModel = profileAssembler.toModel(conversationManager.getProfile(request));
        return ResponseEntity.created(profileModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(profileModel);
    }

    /**
     * Join a new conversation.
     * @param id                          Conversation with id
     * @throws UserBelowMinimumRating     If the user's rating is below conversation's minimum rating
     * @throws UserNotFound               If no user exists with id
     * @throws UserBlocked                If the user is blocked from the conversation
     * @throws ConversationFull           If the conversation is full
     * @throws ConversationNotFound       If no conversation exists with id
     * @throws MessageNotFound            If no conversation exists with id
     * @throws IncorrectPassword          If the password is incorrect
     */
    @PostMapping("/api/conversations/{id}/join")
    public EntityModel<ConversationDataWithLink> join(@PathVariable int id) throws UserBelowMinimumRating, UserNotFound,
            UserBlocked, ConversationFull, ConversationNotFound, MessageNotFound, IncorrectPassword {
        //initiate a request model requesting the conversationId and the userId.
        RequestModel request = new RequestModel();

        //fill in the conversationId
        request.fill(RequestField.CONVERSATION_ID, id);
        //fill in the userId
        request.fill(RequestField.USER_ID, returnId());

        conversationManager.addUser(request);

        // Build response
        ConversationData data = conversationManager.getData(request);
        return dataAssembler.toModel(data);
    }

    /**
     * Leave a conversation.
     * @param id                          Conversation with id
     * @throws UserNotFoundInConversation If the user with given is not in the conversation with given id
     * @throws UserNotFound               If no user exists with id
     * @throws ConversationNotFound       If no conversation exists with id
     */
    @PostMapping("/api/conversations/{id}/leave")
    public EntityModel<ConversationProfile> leave(@PathVariable int id) throws UserNotFound, UserNotFoundInConversation,
            ConversationNotFound {
        //initiate a request model requesting the conversationId and the userId.
        RequestModel request = new RequestModel();

        //fill in the conversationId
        request.fill(RequestField.CONVERSATION_ID, id);
        //fill in the userId
        request.fill(RequestField.USER_ID, returnId());

        conversationManager.removeUser(request);

        // Build response
        ConversationProfile profile = conversationManager.getProfile(request);
        return profileAssembler.toModel(profile);
    }

    /**
     * Edit a conversation.
     * @param form                        A filled in CreateConversationForm
     * @throws InvalidConversationSize    If the provided conversation size is out of range
     * @throws InvalidMinRating           If the provided minimum rating is out of range
     * @throws UserNotFound               If no user exists with id
     * @throws ConversationNotFound       If no conversation exists with id
     * @throws WrongAuthor                If the given user didn't create the given conversation
     * @throws MessageNotFound            If no conversation exists with id
     * @throws IncorrectPassword          If the password is incorrect
     */
    @PostMapping("/api/conversations/{id}/edit")
    public EntityModel<ConversationDataWithLink> edit(@PathVariable int id, @RequestBody CreateConversationForm form)
            throws UserNotFound, ConversationNotFound, InvalidMinRating, WrongAuthor, InvalidConversationSize,
            MessageNotFound, IncorrectPassword {
        //initiate a request model requesting the body, conversationId and the userId.
        RequestModel request = new RequestModel();

        //fill in the body
        request.fill(RequestField.TITLE, form.getTitle());
        request.fill(RequestField.TOPICS, form.getTopics());
        request.fill(RequestField.LOCATION, form.getLocation());
        request.fill(RequestField.LOCATION_RADIUS, form.getLocationRadius());
        request.fill(RequestField.MIN_RATING, form.getMinRating());
        request.fill(RequestField.MAX_SIZE, form.getMaxSize());
        //fill in the conversationId
        request.fill(RequestField.CONVERSATION_ID, id);
        //fill in the userId
        request.fill(RequestField.USER_ID, returnId());

        conversationManager.editConversation(request);

        // Build response
        ConversationData data = conversationManager.getData(request);
        return dataAssembler.toModel(data);
    }

    /**
     * delete a conversation.
     * @param id                             Conversation with id
     * @throws UserNotFound                  If no user exists with id
     * @throws ConversationNotFound          If no conversation exists with id
     * @throws WrongAuthor                   If the given user didn't create the given conversation
     * @throws IncorrectPassword             If the password is incorrect
     */
    @DeleteMapping("/api/conversations/{id}")
    ResponseEntity<?> delete(@PathVariable int id)
            throws UserNotFound, ConversationNotFound, WrongAuthor, IncorrectPassword {
        //initiate a request model requesting the conversationId and the userId.
        RequestModel request = new RequestModel();

        //fill in the messageId
        request.fill(RequestField.CONVERSATION_ID, id);
        //fill in the userId
        request.fill(RequestField.USER_ID, returnId());

        conversationManager.deleteConversation(request);

        return ResponseEntity.noContent().build();
    }

    /**
     * change a conversation's status.
     * @param  id                         Conversation with id
     * @throws UserNotFound               If no user exists with id
     * @throws ConversationNotFound       If no conversation exists with id
     * @throws WrongAuthor                If the given user didn't create the given conversation
     * @throws MessageNotFound            If no conversation exists with id
     * @throws IncorrectPassword          If the password is incorrect
     */
    @PostMapping("/api/conversations/{id}/open")
    public EntityModel<ConversationDataWithLink> changeStatus(@PathVariable int id) throws UserNotFound, WrongAuthor,
            ConversationNotFound, MessageNotFound, IncorrectPassword {
        //initiate a request model requesting the conversationId and the userId.
        RequestModel request = new RequestModel();

        //fill in the conversationId
        request.fill(RequestField.CONVERSATION_ID, id);
        //fill in the userId
        request.fill(RequestField.USER_ID, returnId());

        conversationManager.changeConversationStatus(request);

        // Build response
        ConversationData data = conversationManager.getData(request);
        return dataAssembler.toModel(data);
    }

    /**
     * Add a message to a conversation.
     * @param id                          Conversation with id
     * @param messageBody                 the string body of the given message
     * @throws UserNotFound               If no user exists with id
     * @throws ConversationNotFound       If no conversation exists with id
     * @throws EmptyMessage               If the given message body isEmpty
     * @throws UserNotFoundInConversation If the given user is not in the conversation
     * @throws MessageNotFound            If no conversation exists with id
     * @throws IncorrectPassword          If the password is incorrect
     */
    @PostMapping("/api/conversations/{id}/messages")
    public EntityModel<ConversationDataWithLink> addMessage(@PathVariable int id, @RequestBody String messageBody)
            throws UserNotFoundInConversation, EmptyMessage, ConversationNotFound, UserNotFound, MessageNotFound,
            IncorrectPassword {
        //initiate a request model requesting the body, conversationId and the userId.
        RequestModel request = new RequestModel();

        //fill in the body
        request.fill(RequestField.MESSAGE_BODY, messageBody);
        //fill in the conversationId
        request.fill(RequestField.CONVERSATION_ID, id);
        //fill in the userId
        request.fill(RequestField.USER_ID, returnId());

        conversationManager.addMessage(request);

        // Build response
        ConversationData data = conversationManager.getData(request);
        return dataAssembler.toModel(data);
    }

    /**
     * Edit the message of a conversation.
     * @param cid                            Conversation with cid
     * @param id                             Message with id
     * @param messageBody                    the string body of the given message
     * @throws UserNotFound                  If no user exists with id
     * @throws ConversationNotFound          If no conversation exists with id
     * @throws MessageNotFound               If no message exists with id
     * @throws EmptyMessage                  If the given message body isEmpty
     * @throws WrongAuthor                   If the given user didn't create the given conversation
     * @throws UserNotFoundInConversation    If the user is not found in conversation
     * @throws MessageNotFoundInConversation If the message is not found in conversation
     */
    @PostMapping("/api/conversations/{cid}/messages/{id}/edit")
    public EntityModel<ConversationDataWithLink> editMessage(@PathVariable int cid, @PathVariable int id,
                                                        @RequestBody String messageBody)
            throws UserNotFound, ConversationNotFound, EmptyMessage, WrongAuthor, MessageNotFound,
            UserNotFoundInConversation, MessageNotFoundInConversation, IncorrectPassword {
        //initiate a request model requesting the body, conversationId, messageId and the userId.
        RequestModel request = new RequestModel();

        //fill in the body
        request.fill(RequestField.MESSAGE_BODY, messageBody);
        //fill in the conversationId
        request.fill(RequestField.CONVERSATION_ID, cid);
        //fill in the messageId
        request.fill(RequestField.MESSAGE_ID, id);
        //fill in the userId
        request.fill(RequestField.USER_ID, returnId());

        conversationManager.editMessage(request);

        // Build response
        ConversationData data = conversationManager.getData(request);
        return dataAssembler.toModel(data);
    }

    /**
     * delete a message of a conversation.
     * @param cid                            Conversation with cid
     * @param id                             Message with id
     * @throws UserNotFound                  If no user exists with id
     * @throws ConversationNotFound          If no conversation exists with id
     * @throws MessageNotFound               If no message exists with id
     * @throws WrongAuthor                   If the given user didn't create the given conversation
     * @throws UserNotFoundInConversation    If the user is not found in conversation
     * @throws MessageNotFoundInConversation If the message is not found in conversation
     */
    @DeleteMapping("/api/conversations/{cid}/messages/{id}")
    ResponseEntity<?> deleteMessage(@PathVariable int cid, @PathVariable int id)
            throws UserNotFound, ConversationNotFound, WrongAuthor, MessageNotFound,
            UserNotFoundInConversation, MessageNotFoundInConversation {
        //initiate a request model requesting the conversationId, messageId and the userId.
        RequestModel request = new RequestModel();

        //fill in the conversationId
        request.fill(RequestField.CONVERSATION_ID, cid);
        //fill in the messageId
        request.fill(RequestField.MESSAGE_ID, id);
        //fill in the userId
        request.fill(RequestField.USER_ID, returnId());

        conversationManager.deleteMessage(request);

        return ResponseEntity.noContent().build();
    }

    /**
     * Returns a MessageData for the Message with given id.
     * @param id                        id of Conversation
     * @return                          EntityModel containing Conversation data
     * @throws ConversationNotFound     If no Conversation exists with id
     */
    @GetMapping("/api/messages/{id}")
    public EntityModel<MessageData> getMessageData(@PathVariable int id)
            throws MessageNotFound, ConversationNotFound, MessageNotFoundInConversation {
        RequestModel request = new RequestModel();
        request.fill(RequestField.MESSAGE_ID, id);
        MessageData data = conversationManager.getMessageData(request);
        return messageDataModelAssembler.toModel(data);
    }

}