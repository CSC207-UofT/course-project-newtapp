package com.newts.newtapp.api.forms;

/**
 * A form defining how a delete-user request's json request body should be formatted for Spring to serialize.
 */
public class DeleteUserForm {
    private final int id;
    private final String password;

    public DeleteUserForm(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
