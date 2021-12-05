package com.newts.newtapp.api.controllers.assemblers;

import com.newts.newtapp.api.application.datatransfer.ConversationData;
import org.springframework.hateoas.EntityModel;

import java.util.ArrayList;

/**
 * ConversationData which has its messageData and userProfiles replaced with EntityModels holding
 * messageData or userProfile as data and its link in links
 */
public class ConversationDataWithLink {
    public ArrayList<EntityModel> messageData;
    public ArrayList<EntityModel> userProfiles;

    public final int id;
    public final String title;
    public final ArrayList<String> topics;
    public final String location;
    public int minRating;
    public final int maxSize;
    public final int currSize;
    public Boolean isOpen;

    public ConversationDataWithLink(ArrayList<EntityModel> messageData, ArrayList<EntityModel> userProfiles,
                                    ConversationData conversationData) {
        this.id = conversationData.id;
        this.title = conversationData.title;
        this.topics = conversationData.topics;
        this.location = conversationData.location;
        this.minRating = conversationData.minRating;
        this.maxSize = conversationData.maxSize;
        this.currSize = conversationData.currSize;
        this.isOpen = conversationData.isOpen;
        this.messageData = messageData;
        this.userProfiles = userProfiles;
    }
}
