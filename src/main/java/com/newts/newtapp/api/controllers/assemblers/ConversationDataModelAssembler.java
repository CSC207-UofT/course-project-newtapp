package com.newts.newtapp.api.controllers.assemblers;

import com.newts.newtapp.api.application.datatransfer.ConversationData;
import com.newts.newtapp.api.application.datatransfer.MessageData;
import com.newts.newtapp.api.application.datatransfer.UserProfile;
import com.newts.newtapp.api.controllers.ConversationController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * A RepresentationModelAssembler for ConversationData. Implemented to reduce duplicate code.
 */
@Component
public class ConversationDataModelAssembler
        implements RepresentationModelAssembler<ConversationData,
        EntityModel<ConversationDataWithLink>> {

    private final UserProfileModelAssembler profileAssembler;
    private final MessageDataModelAssembler messageAssembler;

    public ConversationDataModelAssembler(UserProfileModelAssembler profileAssembler, MessageDataModelAssembler messageAssembler) {
        this.profileAssembler = profileAssembler;
        this.messageAssembler = messageAssembler;
    }

    /**
     * @param data      ConversationData to be contained in EntityModel
     * @return          EntityModel containing provided ConversationData and appropriate links
     */
    @Override
    public @NonNull EntityModel<ConversationDataWithLink> toModel(@NonNull ConversationData data) {
        ArrayList<Link> links = new ArrayList<>();
        try {
            // Add appropriate links from this ConversationData
            links.add(linkTo(methodOn(ConversationController.class).getData(data.id)).withSelfRel());
        } catch (Exception e) {
            // No exception will ever be thrown at this point in normal use of this class as an exception would have
            // been thrown earlier during application logic.
        }

        ArrayList<EntityModel> userProfiles = new ArrayList<EntityModel>();
        ArrayList<EntityModel> messageDatas = new ArrayList<EntityModel>();

        for (UserProfile profile: data.userProfiles) {
            userProfiles.add(profileAssembler.toModel(profile));
        }

        for (MessageData message: data.messageData) {
            messageDatas.add(messageAssembler.toModel(message));
        }

        return EntityModel.of(new ConversationDataWithLink(messageDatas, userProfiles, data), links);
    }


}