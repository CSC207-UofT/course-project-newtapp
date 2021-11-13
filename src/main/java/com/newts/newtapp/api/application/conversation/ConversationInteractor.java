package com.newts.newtapp.api.application.conversation;
import com.newts.newtapp.api.application.InputBoundary;
import com.newts.newtapp.entities.Conversation;

/**
 * An abstract ConversationInteractor object. Generally to be extended as a specific Conversation usecase.
 * Requires simply that an implementing class stores a Conversation object and handles requests.
 */
public abstract class ConversationInteractor implements InputBoundary {
    private Conversation conversation;
}