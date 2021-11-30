package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.application.RequestField;
import com.newts.newtapp.api.application.RequestModel;
import com.newts.newtapp.api.errors.*;
import com.newts.newtapp.api.gateways.TestConversationRepository;
import com.newts.newtapp.api.gateways.TestUserRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeleteTest {
    TestUserRepository testUserRepository;
    TestConversationRepository testConversationRepository;
    Create create;
    Delete delete;

    @Before
    public void setUp() throws InvalidUsername, UserAlreadyExists, InvalidPassword {
        testUserRepository = new TestUserRepository();
        testConversationRepository = new TestConversationRepository();
        create = new com.newts.newtapp.api.application.user.Create(testUserRepository);
        delete = new com.newts.newtapp.api.application.user.Delete(testUserRepository);
        RequestModel r = new RequestModel();
        r.fill(RequestField.USERNAME, "test");
        r.fill(RequestField.PASSWORD, "test123");
        r.fill(RequestField.INTEREST, "tests");
        create.request(r);
    }

    @Test(timeout = 500)
    public void testDelete() throws UserNotFound, IncorrectPassword, ConversationNotFound{
        RequestModel r2 = new RequestModel();
        r2.fill(RequestField.USER_ID, 1);
        r2.fill(RequestField.PASSWORD, "test123");
        delete.request(r2);
        assertFalse(testUserRepository.findById(1).isPresent());
    }
}
