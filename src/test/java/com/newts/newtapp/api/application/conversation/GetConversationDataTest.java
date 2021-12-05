package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.application.datatransfer.ConversationData;
import com.newts.newtapp.api.errors.ConversationNotFound;
import com.newts.newtapp.api.errors.IncorrectPassword;
import com.newts.newtapp.api.errors.MessageNotFound;
import com.newts.newtapp.api.errors.UserNotFound;
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

public class GetConversationDataTest {
    TestConversationRepository c;
    TestMessageRepository m;
    TestUserRepository u;
    GetConversationData g;
    Conversation testConversation;
    User testUser;
    Message testMessage;

    @Before
    public void setUp() {
        c = new TestConversationRepository();
        m = new TestMessageRepository();
        u = new TestUserRepository();

        testConversation = new Conversation();
        testConversation.setMaxSize(1);
        testUser = new User(1, "testUser", "password", new ArrayList<>());
        testConversation.addUser(testUser);
        testMessage = new Message(-1, "", 1, 0);
        testConversation.addMessage(testMessage);

        u.save(testUser);
        m.save(testMessage);
        c.save(testConversation);

        g = new GetConversationData(c, m, u);
    }

    @Test(timeout = 50)
    public void testGetConversationData() throws UserNotFound, MessageNotFound, IncorrectPassword, ConversationNotFound {
        RequestModel r = new RequestModel();
        r.fill(RequestField.CONVERSATION_ID, -1);

        ConversationData actualData = g.request(r);

        Assert.assertEquals(testConversation.getId(), actualData.id);
        Assert.assertEquals(1, actualData.userProfiles.get(0).id);
        Assert.assertEquals(-1, actualData.messageData.get(0).id);
    }
}
