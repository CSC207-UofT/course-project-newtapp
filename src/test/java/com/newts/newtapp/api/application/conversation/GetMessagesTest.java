package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.errors.ConversationNotFound;
import com.newts.newtapp.api.errors.MessageNotFound;
import com.newts.newtapp.api.gateways.TestConversationRepository;
import com.newts.newtapp.api.gateways.TestMessageRepository;
import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.Message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class GetMessagesTest {
    TestConversationRepository c;
    TestMessageRepository m;
    Message testMessage;
    Conversation testConversation;
    GetMessages g;

    @Before
    public void setUp() {
        c = new TestConversationRepository();
        m = new TestMessageRepository();
        testMessage = new Message();
        testConversation = new Conversation();
        testMessage.setId(1);
        testConversation.addMessage(testMessage);

        c.save(testConversation);
        m.save(testMessage);

        g = new GetMessages(c, m);
    }

    @Test(timeout=50)
    public void testGetMessages() throws MessageNotFound, ConversationNotFound {
        RequestModel r = new RequestModel();

        r.fill(RequestField.CONVERSATION_ID, -1);

        ArrayList<Message> messages = g.request(r);

        Assert.assertEquals(1, messages.get(0).getId());
    }
}
