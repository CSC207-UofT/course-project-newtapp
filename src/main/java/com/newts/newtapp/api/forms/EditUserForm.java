package com.newts.newtapp.api.controllers.forms;

/**
 * A form defining how an edit user request's json request body should be formatted for Spring to serialize.
 */
public class EditUserForm {
    private final int id;
    private final String userKey;
    private final String userValue;

    public EditUserForm(int id, String userKey, String userValue) {
        this.id = id;
        this.userKey = userKey;
        this.userValue = userValue;
    }

    public int getId(){return id;}

    public String getUserKey() {
        return userKey;
    }

    public String getUserValue() {
        return userValue;
    }
}
