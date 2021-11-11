package com.newts.newtapp.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message implements Serializable {
    private String id;
    private String body;
    private User author;
    private String writtenAt;
    private String lastUpdatedAt;

    public Message(String id, String body, User author) {
        this.id = id;
        this.body = body;
        this.author = author;
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.writtenAt = LocalDateTime.now().format(date);
        this.lastUpdatedAt = LocalDateTime.now().format(date);
    }

    public Message() {
    }

    /**
     * Return the body of the message.
     *
     * @return a string representing the body of the message
     */
    public String getBody() {
        return this.body;
    }

    /**
     * Return the author of the message.
     *
     * @return a string representing the author of the message
     */
    public String getAuthor() {
        return this.author.getUsername();
    }

    /**
     * Return the time and date at which the message was written.
     *
     * @return a LocalDateTime representing the date and time at which the message was created.
     */
    public String getWrittenAt() {
        return this.writtenAt;
    }

    /**
     * Return the time and date at which the message was last updated.
     *
     * @return a LocalDateTime representing the date and time at which the message was last updated.
     */
    public String getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    /**
     * Set the body of the message.
     *
     * @param body the body to set.
     */
    public void setBody(String body) {
        this.body = body;
        this.setLastUpdatedAt();
    }

    /**
     * Set the time at which the message was last updated.
     * This is called everytime setBody is called
     */
    private void setLastUpdatedAt(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.lastUpdatedAt = LocalDateTime.now().format(date);
    }

    /**
     * Returns message ID.
     * @return String representing messages unique id.
     */
    public String getId(){
        return id;
    }
}
