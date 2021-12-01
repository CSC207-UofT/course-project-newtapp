package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.application.UserProfile;
import com.newts.newtapp.api.errors.ConversationNotFound;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.api.gateways.TestConversationRepository;
import com.newts.newtapp.api.gateways.TestUserRepository;
import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class GetUserListTest {
    TestConversationRepository c;
    TestUserRepository u;
    GetUserList get;
    User testUser;

    @Before
    public void setUp() {
        c = new TestConversationRepository();
        u = new TestUserRepository();

        Conversation testConversation = new Conversation();
        testConversation.setMaxSize(1);
        testUser = new User();
        u.save(testUser);
        testConversation.addUser(testUser);
        c.save(testConversation);
        get = new GetUserList(c, u);
    }

    @Test(timeout = 50)
    public void testGetUserList() throws UserNotFound, ConversationNotFound {
        RequestModel r = new RequestModel();
        r.fill(RequestField.CONVERSATION_ID, -1);
        ArrayList<UserProfile> actualList = get.request(r);
        Assert.assertEquals(actualList.get(0).id, testUser.getId());
    }
}
