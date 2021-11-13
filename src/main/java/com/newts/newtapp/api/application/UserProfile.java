package com.newts.newtapp.api.application;

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

    public UserProfile(int id,
                       String username,
                       String location,
                       ArrayList<String> interests,
                       boolean loginStatus,
                       ArrayList<Integer> following,
                       ArrayList<Integer> followers,
                       ArrayList<Integer> conversations) {
        this.id = id;
        this.username = username;
        this.location = location;
        this.interests = interests;
        this.loginStatus = loginStatus;
        this.following = following;
        this.followers = followers;
        this.conversations = conversations;
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
