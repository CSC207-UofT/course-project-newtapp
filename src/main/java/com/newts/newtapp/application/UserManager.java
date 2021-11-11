package com.newts.newtapp.application;
import com.newts.newtapp.entities.User;

import com.newts.newtapp.application.user.*;

/**
 * An object representing an UserManager of the application.
 */
public class UserManager {
    /**
     * The User this UserManager is managing.
     */
    private User user;

    /**
     * Creates a new user according to the given RequestModel and sets this UserManager's user accordingly.
     * @param request   RequestModel containing new User information.
     */
    public void createUser(RequestModel request) {
        CreateUser createUser = new CreateUser();
        createUser.request(request);
        user = createUser.getUser();
    }

    /**
     * Logs in a user according to RequestModel information and sets this UserManager's user accordingly.
     */
    public void login(RequestModel request) {
        LoginUser loginUser = new LoginUser();
        loginUser.request(request);
        user = loginUser.getUser();
    }

    public void logout() {
        user.logOut();
    }

    /**
     * Deletes a user according to the given RequestModel and sets this UserManager's user to null.
     * @param request   RequestModel containing delete User information.
     */
    public void deleteUser(RequestModel request) {
        DeleteUser deleteUser = new DeleteUser();
        deleteUser.request(request);
    }

    /**
     * Finds a user according to the given RequestModel and adds it to the friends list of the user.
     * @param request   RequestModel containing addFriend User information.
     */
    public void addFriend(RequestModel request) {
        AddFriend addFriend = new AddFriend();
        addFriend.request(request);
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
