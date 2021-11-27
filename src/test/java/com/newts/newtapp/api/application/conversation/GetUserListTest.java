package com.newts.newtapp.api.application.conversation;

import com.newts.newtapp.api.application.RequestField;
import com.newts.newtapp.api.application.RequestModel;
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
        c.save(testConversation);
        testUser = new User();
        u.add(testUser);
        testConversation.addUser(testUser);
        get = new GetUserList(c, u);
    }

    @Test(timeout = 50)
    public void testGetUserList() throws UserNotFound, ConversationNotFound {
        RequestModel r = new RequestModel();
        r.fill(RequestField.CONVERSATION_ID, -1);
        ArrayList<UserProfile> actualList = get.request(r);
        ArrayList<UserProfile> expectedList = new ArrayList<>();
        expectedList.add(new UserProfile(testUser));
        Assert.assertEquals(actualList, expectedList);
    }
}
