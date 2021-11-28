package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.application.RequestField;
import com.newts.newtapp.api.application.RequestModel;
import com.newts.newtapp.api.application.conversation.Create;
import com.newts.newtapp.api.errors.InvalidConversationSize;
import com.newts.newtapp.api.errors.InvalidMinRating;
import com.newts.newtapp.api.gateways.TestConversationRepository;
import com.newts.newtapp.entities.Conversation;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class CreateTest {
    TestConversationRepository c;
    Create create;

    @Before
    public void setUp() {
        c = new TestConversationRepository();
        create = new Create(c);
    }

    @Test(timeout = 50)
    public void testCreate() throws InvalidMinRating, InvalidConversationSize {
        RequestModel r = new RequestModel();
        r.fill(RequestField.TITLE, "");
        r.fill(RequestField.TOPIC, "");
        r.fill(RequestField.LOCATION, "");
        r.fill(RequestField.LOCATION_RADIUS, 0);
        r.fill(RequestField.MIN_RATING, 0);
        r.fill(RequestField.MAX_SIZE, 1);
        r.fill(RequestField.CLOSING_TIME, "");
        r.fill(RequestField.USER_ID, -1);
        create.request(r);
        assertTrue(c.findById(1).isPresent());
        Conversation conversation = c.findById(1).get();
        assertTrue(conversation.getIsOpen());
    }


}
