package com.newts.newtapp.api.application.datatransfer;

import com.newts.newtapp.entities.Conversation;

import java.util.ArrayList;

/**
 * A condensed view of data in a Conversation, for passing Conversation data visible to members of a conversation,
 * without revealing secure information.
 * This is just a data storage object.
 */
public class ConversationData {
    public ArrayList<MessageData> message_data;
    public ArrayList<UserProfile> user_profile;

    public final int id;
    public final String title;
    public final ArrayList<String> topics;
    public final String location;
    public int minRating;
    public final int maxSize;
    public final int currSize;
    public Boolean isOpen;

    public ConversationData(ArrayList<MessageData> messageData, ArrayList<UserProfile> userProfiles, Conversation conversation) {
        this.id = conversation.getId();
        this.title = conversation.getTitle();
        this.topics = conversation.getTopics();
        this.location = conversation.getLocation();
        this.minRating = conversation.getMinRating();
        this.maxSize = conversation.getMaxSize();
        this.currSize = conversation.getNumUsers() / conversation.getMaxSize();
        this.isOpen = conversation.getIsOpen();
        this.message_data = messageData;
        this.user_profile = userProfiles;
    }
}
