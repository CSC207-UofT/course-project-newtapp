package com.newts.newtapp.application;

/**
 * Exception messages for use in the application module.
 */
public class ApplicationExceptions{
    public static final String BELOW_MINIMUM_RATING_ERROR = "The user has lower than the necessary rating to join this " +
            "conversation";
    public static final String CONVERSATION_FULL_ERROR = "The conversation is already at maximum capacity.";
    public static final String NO_SUCH_CONVERSATION_ERROR = "No such conversation exists";
    public static final String NO_SUCH_USER_ERROR = "No such user exists";
    public static final String SAME_USER_NAME_ERROR = "Same usernames are given";
}
