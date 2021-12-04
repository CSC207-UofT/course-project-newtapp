package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.errors.ConversationNotFound;
import com.newts.newtapp.api.errors.MessageNotFound;
import com.newts.newtapp.api.errors.MessageNotFoundInConversation;
import com.newts.newtapp.api.errors.WrongAuthor;
import com.newts.newtapp.api.gateways.TestConversationRepository;
import com.newts.newtapp.api.gateways.TestMessageRepository;
import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.Message;
import com.newts.newtapp.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class DeleteMessageTest {
    TestConversationRepository c;
    TestMessageRepository m;
    User testUser;
    Message testMessage;
    Message testMessageTwo;
    Conversation testConversation;
    DeleteMessage d;

    @Before
    public void setUp() {
        c = new TestConversationRepository();
        m = new TestMessageRepository();
        testUser = new User();
        testUser.setId(3);
        testMessage = new Message();
        testMessageTwo = new Message();
        testMessage.setId(1);
        testMessageTwo.setId(2);
        testMessageTwo.setAuthor(3);
        testConversation = new Conversation();
        testConversation.setId(1);
        testConversation.addMessage(testMessage);
        testConversation.addMessage(testMessageTwo);

        c.save(testConversation);
        m.save(testMessage);
        m.save(testMessageTwo);

        d = new DeleteMessage(c, m);
    }

    @Test(timeout=50)
    public void testGetMessages() throws ConversationNotFound, MessageNotFound, WrongAuthor, MessageNotFoundInConversation {
        RequestModel r = new RequestModel();

        r.fill(RequestField.CONVERSATION_ID, 1);
        r.fill(RequestField.MESSAGE_ID, 2);
        r.fill(RequestField.USER_ID, 3);

        d.request(r);

        assertTrue(c.findById(1).isPresent());
        Assert.assertEquals(1, c.findById(1).get().getMessages().size());
    }
}