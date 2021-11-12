package com.newts.newtapp.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * An object representing a User of the application.
 */
@Entity
@Table(name = "users")
public class User {
    /**
     * This User's unique identifier.
     */
    private int id;

    /**
     * This User's username, used for logging in and is visible to other Users.
     */
    private String username;

    /**
     * This User's password, used for logging in.
     */
    private String password;

    /**
     * This User's location, used for finding local conversations.
     */
    private String location;

    /**
     * A list of this user's interests.
     */
    private final List<String> interests;

    /**
     * This user's rating, simply an average of all ratings given to this user by others. Each user starts with one
     * rating of 5. A rating is a double in the range of 0 to 5.
     */
    private double rating;

    /**
     * Total number of ratings this user has received. This is used for computing the user's rating. Is 1 initially.
     */
    private int numRatings;

    /**
     * True if and only if this user is currently logged in.
     */
    private boolean loginStatus;

    /**
     * A list of unique user identifiers corresponding to this User's friends.
     */
    private final List<Integer> friends;

    /**
     * A list of unique conversation identifiers corresponding to this User's active conversations.
     */
    private final List<Integer> conversations;

    /**
     * Create a new User with given User information.
     * @param username  The user's username.
     * @param password  The user's password.
     * @param interests A List of the user's initial specified interests.
     * @param id        The user's unique id.
     */
    public User(String username,
                String password,
                List<String> interests,
                int id){
        this.username = username;
        this.password = password;
        this.interests = interests;
        this.id = id;
        location = null;
        rating = 5;
        numRatings = 1;
        loginStatus = false;
        friends = new ArrayList<>();
        conversations = new ArrayList<>();
    }

    /**
     * Empty User constructor- for testing purposes.
     */
    public User() {
        username = null;
        password = null;
        interests = new ArrayList<>();
        id = 0;
        rating = 0;
        numRatings = 0;
        loginStatus = false;
        friends = new ArrayList<>();
        conversations = new ArrayList<>();
    }

    /**
     * Getter methods for the login status of the user
     * @return Returns loginStatus of user.
     */
    public boolean getLoginStatus(){
        return loginStatus;
    }

    /**
     * Getter method for user's ID.
     * @return Returns user ID.
     */
    public int getId(){
        return id;
    }

    /**
     * Setter method for user's ID.
     * @param id    The new id to assign this user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter method for user's username.
     * @return Returns user's username.
     */
    public String getUsername(){
        return username;
    }

    /**
     * Setter method for username
     * @param username Username to be set
     */
    public void setUsername(String username) {this.username = username;}

    /**
     * Getter method for user's password.
     * NOTE: This is probably a bad idea to have.
     * @return Returns user's password
     */
    public String getPassword(){
        return password;
    }
    /**
     * Getter method for user's last updated location.
     * NOTE: Wasn't sure what type the location data would be,
     * using type Object for now, to be changed later.
     * @return Returns user's last updated location.
     */
    public String getLocation(){
        return location;
    }

    /**
     * Setter method for the user's password
     * @param password Password to be set
     */

    public void setPassword(String password){
        if(password.length() >= 6) {
            this.password = password;
        }
    }

    /**
     * Setter method for user's location.
     * @param location Location of user (Type to be changed)
     */
    public void setLocation(String location){
        this.location = location;
    }

    /**
     * Getter method for ArrayList of user interest.
     * @return Returns ArrayList of user interests.
     */
    public List<String> getInterests() {
        return interests;
    }

    /**
     * Adds supplied interest to user's interests ArrayList
     * @param interest New interest to be added
     */
    public void addInterests(String interest){
        interests.add(interest);
    }

    /**
     * Removes specified interest from user's interests
     * @param interest Interest to be removed
     */
    public void removeInterest (String interest){
        int index = interests.indexOf(interest);
        if (index != -1)
            interests.remove(index);
    }

    /**
     * Getter method which returns the user's average rating
     * @return User's average rating
     */
    public double getRating() {
        return rating/numRatings;
    }

    /**
     * Adds rating to user's total rating and increases
     * The number of ratings by one.
     * @param rating Rating to be added
     */
    public void addRating(float rating){
        this.rating += rating;
        numRatings ++;
    }

    /**
     * Sets user loginStatus to True
     */
    public void logIn(){
        loginStatus = true;
    }

    /**
     * Sets user loginStatus to false
     */
    public void logOut(){
        loginStatus = false;
    }

    /**
     * Adds specified user to user's friendslist
     * @param friend User to be added as friend
     */
    public void addFriend(User friend) {
        friends.add(friend.id);
    }

    /**
     * Removes specified user from user's friendslist
     * @param friend User to be removed
     */
    public void removeFriend(User friend){
        int index = friends.indexOf(friend.getId());
        if (index != -1)
            friends.remove(index);
    }

    /**
     * Getter method which returns user's friendslist
     * @return Returns user's friendslist which is an ArrayList of users
     */
    public List<Integer> getFriends(){
        return friends;
    }

    /**
     * Adds specified conversation object to user's list of active conversations
     * @param conversation Conversation to be added
     */
    public void addConversation(Conversation conversation){
        conversations.add(conversation.getId());
    }

    /**
     * Removes specified conversation from user's list
     * of active conversations.
     * @param conversation Conversation to be removed
     */
    public void removeConversation(Conversation conversation){
        int index = conversations.indexOf(conversation.getId());
        if(index != -1)
            conversations.remove(conversation.getId());
    }

    /**
     * Returns list of user's active conversations.
     * @return ArrayList of user's active conversations.
     */
    public List<Integer> getConversations(){
        return conversations;
    }

}