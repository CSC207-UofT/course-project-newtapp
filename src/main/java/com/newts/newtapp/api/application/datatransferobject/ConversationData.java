package com.newts.newtapp.api.application.datatransferobject;

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
    public int location_radius;
    public int min_rating;
    public final int max_size;
    public final int curr_size;
    public Boolean is_open;

    public ConversationData(ArrayList<MessageData> message_data, ArrayList<UserProfile> user_profiles, Conversation conversation) {
        this.id = conversation.getId();
        this.title = conversation.getTitle();
        this.topics = conversation.getTopics();
        this.location = conversation.getLocation();
        this.location_radius = conversation.getLocationRadius();
        this.min_rating = conversation.getMinRating();
        this.max_size = conversation.getMaxSize();
        this.curr_size = conversation.getNumUsers() / conversation.getMaxSize();
        this.is_open = conversation.getIsOpen();

        this.message_data = message_data;
        this.user_profile = user_profiles;


    }
}
