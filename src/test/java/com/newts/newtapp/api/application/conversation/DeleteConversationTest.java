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
public class DeleteConversationTest {
    TestConversationRepository c;
    TestMessageRepository m;
    TestUserRepository u;
    Conversation testConversation;
    User testUser;
    Delete delete;

    @Before
    public void setUp() {
        c = new TestConversationRepository();
        m = new TestMessageRepository();
        u = new TestUserRepository();

        testConversation = new Conversation();
        testUser = new User();
        c.save(testConversation);
        delete = new Delete(c,m,u);
    }

    @Test(timeout = 50)
    public void testDeleteConversation(){
        RequestModel request = new RequestModel();

    }
}
