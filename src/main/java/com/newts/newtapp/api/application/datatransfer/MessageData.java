package com.newts.newtapp.api.application.datatransfer;


import com.newts.newtapp.entities.Message;

public class MessageData {
    public int id;
    public String body;
    public int author;
    public String written_at;
    public String last_updated_at;

    public MessageData(Message message) {
        this.id = message.getId();
        this.body = message.getBody();
        this.author = message.getAuthor();
        this.written_at = message.getWrittenAt();
        this.last_updated_at = message.getLastUpdatedAt();
    }
}
