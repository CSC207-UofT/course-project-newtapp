package com.newts.newtapp.api.controllers.forms;

import java.util.ArrayList;

/**
 * A form defining how an edit user request's json request body should be formatted for Spring to serialize.
 */
public class EditUserForm {
    private final int id;
    private final String username;
    private final String location;
    private final ArrayList<String> interests;

    public EditUserForm(int id, String username, String location, ArrayList<String> interests) {
        this.id = id;
        this.username = username;
        this.location = location;
        this.interests = interests;
    }

    public int getId(){return id;}

    public String getUsername() {
        return username;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<String> getInterests() {
        return interests; }
}
