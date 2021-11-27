package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.application.RequestField;
import com.newts.newtapp.api.application.RequestModel;
import com.newts.newtapp.api.errors.ConversationNotFound;
import com.newts.newtapp.api.gateways.TestConversationRepository;
import com.newts.newtapp.entities.Conversation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ChangeStatusTest {
    TestConversationRepository c;
    ChangeStatus change;

    @Before
    public void setUp() {
        c = new TestConversationRepository();
        Conversation testConversation = new Conversation();
        c.save(testConversation);
        change = new ChangeStatus(c);
    }

    @Test(timeout = 50)
    public void testChangeStatus() throws ConversationNotFound {
        RequestModel r = new RequestModel();
        r.fill(RequestField.CONVERSATION_ID, -1);
        change.request(r);
        assertTrue(c.findById(-1).isPresent());
        assertTrue(c.findById(-1).get().getIsOpen());
    }
}
