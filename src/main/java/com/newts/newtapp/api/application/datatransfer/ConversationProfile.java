package com.newts.newtapp.api.application.datatransfer;

import com.newts.newtapp.entities.Conversation;

import java.util.ArrayList;

/**
 * A condensed view of a Conversation, for passing Conversation information to a client browser without revealing
 * secure information.
 * This is just a data storage object.
 */
public class ConversationProfile {
    public final int id;
    public final String title;
    public final ArrayList<String> topics;
    public final String location;
    public final int max_size;
    public final int curr_size;

    public ConversationProfile(Conversation conversation) {
        this.id = conversation.getId();
        this.title = conversation.getTitle();
        this.topics = conversation.getTopics();
        this.location = conversation.getLocation();
        this.max_size = conversation.getMaxSize();
        this.curr_size = conversation.getNumUsers() / conversation.getMaxSize();

    }
}
