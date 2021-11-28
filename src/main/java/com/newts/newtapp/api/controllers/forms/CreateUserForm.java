package com.newts.newtapp.api.controllers.forms;

/**
 * A form defining how a create user request's json request body should be formatted for Spring to serialize.
 */
public class CreateUserForm {
    private final String username;
    private final String password;
    private final String interest;

    public CreateUserForm(String username, String password, String interest) {
        this.username = username;
        this.password = password;
        this.interest = interest;
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
}
