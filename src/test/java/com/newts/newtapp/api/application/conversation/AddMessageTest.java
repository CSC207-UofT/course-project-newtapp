package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.errors.*;
import com.newts.newtapp.api.gateways.TestConversationRepository;
import com.newts.newtapp.api.gateways.TestMessageRepository;
import com.newts.newtapp.api.gateways.TestUserRepository;
import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.Message;
import com.newts.newtapp.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class AddMessageTest {
    TestConversationRepository c;
    TestUserRepository u;
    TestMessageRepository m;
    AddMessage a;
    Conversation testConversation;
    User testUser;

    @Before
    public void setUp() {
        c = new TestConversationRepository();
        m = new TestMessageRepository();
        u = new TestUserRepository();

        testConversation = new Conversation();
        testUser = new User();
        c.save(testConversation);
        u.save(testUser);

        a = new AddMessage(c,m,u);
    }

    @Test(timeout = 50)
    public void testAddMessage() throws ConversationNotFound, EmptyMessage {
        RequestModel r = new RequestModel();
        r.fill(RequestField.CONVERSATION_ID, testConversation.getId());
        r.fill(RequestField.USER_ID, testUser.getId());
        r.fill(RequestField.MESSAGE_BODY, "Hello");

        a.request(r);

        assertTrue(c.findById(-1).isPresent());
        Conversation checkConversation = c.findById(-1).get();
        ArrayList<Integer> messageList = checkConversation.getMessages();
        int actualMessageId = messageList.get(0);
        Assert.assertEquals(0, actualMessageId);

        assertTrue(m.findById(1).isPresent());
        Message checkMessage = m.findById(1).get();

        Assert.assertEquals("Hello", checkMessage.getBody());
    }
}