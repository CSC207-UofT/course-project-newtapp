package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.errors.ConversationNotFound;
import com.newts.newtapp.api.gateways.TestConversationRepository;
import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;


public class EditTest {
    TestConversationRepository c;
    User testUser;
    Conversation testConversation;
    Edit e;

    @Before
    public void setUp() {
        c = new TestConversationRepository();
        testUser = new User();
        testConversation = new Conversation();
        testConversation.setId(1);
        testConversation.setTitle("old");
        testConversation.setLocation("USA");

        c.save(testConversation);

        e = new Edit(c);
    }

    @Test(timeout=50)
    public void testGetMessages() throws ConversationNotFound {
        RequestModel r = new RequestModel();

        r.fill(RequestField.CONVERSATION_ID, 1);
        r.fill(RequestField.TITLE, "new");
        r.fill(RequestField.TOPICS, new ArrayList<>());
        r.fill(RequestField.LOCATION, "Toronto");
        r.fill(RequestField.LOCATION_RADIUS, 1);
        r.fill(RequestField.MIN_RATING,1);

        e.request(r);

        assertTrue(c.findById(1).isPresent());
        Assert.assertEquals("new", c.findById(1).get().getTitle());
        Assert.assertEquals("Toronto", c.findById(1).get().getLocation());
    }
}