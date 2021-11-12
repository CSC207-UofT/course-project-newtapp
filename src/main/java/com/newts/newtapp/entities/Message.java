package com.newts.newtapp.entities;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class representing a message
 */
@Entity
@Table(name = "message")
// A custom type in order to save ArrayLists to the database.
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class Message {

    /**
     * This Message's unique id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    /**
     * This message's body.
     */
    @Column(name = "body", columnDefinition = "text")
    private String body;

    /**
     * This message's author.
     */
    @Column(name = "author", columnDefinition = "integer")
    private int author;

    /**
     * This message's time at which it was written.
     */
    @Column(name = "written_at", columnDefinition = "text")
    private String writtenAt;

    /**
     * This message's time at which it was last updated.
     */
    @Column(name = "last_updated_at", columnDefinition = "text")
    private String lastUpdatedAt;

    /**
     * Create a new message with given id, body, and author.
     * @param id        Unique id for this message
     * @param body      Body of this message
     * @param author    Author of this message
     */
    public Message(int id, String body, User author) {
        this.id = id;
        this.body = body;
        this.author = author.getId();
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
     * Return the User id of the author of the message.
     *
     * @return User id of the author of the message
     */
    public int getAuthor() {
        return this.author;
    }

    /**
     * Set the author of this message.
     * @param author    User id of author
     */
    public void setAuthor(int author) { this.author = author; }

    /**
     * Return the time and date at which the message was written.
     *
     * @return a String representing the date and time at which the message was created.
     */
    public String getWrittenAt() {
        return this.writtenAt;
    }

    /**
     * Set the time and date at which this message was written.
     * @param date  date/time this message was written at
     */
    public void setWrittenAt(String date) { this.writtenAt = date; }

    /**
     * Return the time and date at which the message was last updated.
     *
     * @return a String representing the date and time at which the message was last updated.
     */
    public String getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    /**
     * Set the time and date at which this message was last updated.
     * @param date  date/time this message was last updated at
     */
    public void setLastUpdatedAt(String date) { this.lastUpdatedAt = date; }

    /**
     * Set the body of the message.
     *
     * @param body the body to set.
     */
    public void setBody(String body) {
        this.body = body;
        this.update();
    }

    /**
     * Set the time at which the message was last updated.
     * This is called everytime setBody is called
     */
    private void update(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.lastUpdatedAt = LocalDateTime.now().format(date);
    }

    /**
     * Returns this message's unique id.
     * @return Message's unique id.
     */
    public int getId(){
        return id;
    }

    /**
     * Set this message's unique id.
     * @param id    unique message id
     */
    public void setId(int id) { this.id = id; }
}
