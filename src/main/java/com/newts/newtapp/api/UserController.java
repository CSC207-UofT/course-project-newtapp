package com.newts.newtapp.api;

import com.newts.newtapp.api.application.RequestField;
import com.newts.newtapp.api.application.RequestModel;
import com.newts.newtapp.api.application.UserManager;
import com.newts.newtapp.api.application.UserProfile;
import com.newts.newtapp.api.errors.UserNotFound;
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

    @GetMapping("/users/{id}")
    UserProfile one(@PathVariable int id) throws UserNotFound {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USERID, id);
        return userManager.getProfile(request);
    }

    @GetMapping("/users/getRelevantConversations")
    Conversation[] getRelevantConversations(@RequestParam int userId, @RequestParam int locationRadius)
            throws UserNotFound {
        RequestModel request = new RequestModel();
        request.fill(RequestField.USERID, userId);
        request.fill(RequestField.LOCATION_RADIUS, locationRadius);
        return userManager.getRelevantConversations(request);
    }
}
