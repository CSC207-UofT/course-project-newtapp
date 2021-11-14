package com.newts.newtapp.api.application;

import com.newts.newtapp.entities.User;

import java.util.ArrayList;

/**
 * A condensed view of a User, for passing User information to a client browser without revealing secure information.
 */
public class UserProfile {
    private final int id;
    private final String username;
    private final String location;
    private final ArrayList<String> interests;
    private final boolean loginStatus;
    private final ArrayList<Integer> following;
    private final ArrayList<Integer> followers;
    private final ArrayList<Integer> conversations;

    public UserProfile(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.location = user.getLocation();
        this.interests = user.getInterests();
        this.loginStatus = user.getLoginStatus();
        this.following = user.getFollowing();
        this.followers = user.getFollowers();
        this.conversations = user.getConversations();
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<String> getInterests() {
        return interests;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public ArrayList<Integer> getFollowing() {
        return following;
    }

    public ArrayList<Integer> getFollowers() {
        return followers;
    }

    public ArrayList<Integer> getConversations() {
        return conversations;
    }
}
