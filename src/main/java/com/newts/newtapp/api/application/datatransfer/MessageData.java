package com.newts.newtapp.api.application.datatransfer;


import com.newts.newtapp.entities.Message;

public class MessageData {
    public int id;
    public String body;
    public int author;
    public String writtenAt;
    public String lastUpdatedAt;

    public MessageData(Message message) {
        this.id = message.getId();
        this.body = message.getBody();
        this.author = message.getAuthor();
        this.writtenAt = message.getWrittenAt();
        this.lastUpdatedAt = message.getLastUpdatedAt();
    }
}
