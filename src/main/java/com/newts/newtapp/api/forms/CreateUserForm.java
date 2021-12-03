package com.newts.newtapp.api.forms;

import java.util.ArrayList;

/**
 * A form defining how a create user request's json request body should be formatted for Spring to serialize.
 */
public class CreateUserForm {
    private final String username;
    private final String password;
    private final String interest;
    private final ArrayList<String> interests;
    private final String location;

    public CreateUserForm(String username, String password, String interest, ArrayList<String> interests, String location) {
        this.username = username;
        this.password = password;
        this.interest = interest;
        this.interests = interests;
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getInterest() {
        return interest;
    }
    public ArrayList<String> getInterests() {
        return interests;
    }
    public String getgetLocation() {
        return location;
    }
}
