package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.application.RequestField;
import com.newts.newtapp.api.application.RequestModel;
import com.newts.newtapp.api.errors.*;
import com.newts.newtapp.api.gateways.TestUserRepository;
import com.newts.newtapp.entities.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class LoginLogoutTest {
    TestUserRepository testUserRepository;
    Create create;
    Login login;
    Logout logout;

    @Before
    public void setUp() throws UserNotFound, SameUser, AlreadyFollowingUser, InvalidUsername, UserAlreadyExists,
            InvalidPassword {
        testUserRepository = new TestUserRepository();
        create = new com.newts.newtapp.api.application.user.Create(testUserRepository);
        login = new com.newts.newtapp.api.application.user.Login(testUserRepository);
        logout = new com.newts.newtapp.api.application.user.Logout(testUserRepository);
        RequestModel r = new RequestModel();
        r.fill(RequestField.USERNAME, "test");
        r.fill(RequestField.PASSWORD, "test1234");
        r.fill(RequestField.INTEREST, "tests");
        create.request(r);
    }

    @Test(timeout = 500)
    public void testLoginLogout() throws UserNotFound, IncorrectPassword {
        RequestModel r2 = new RequestModel();
        r2.fill(RequestField.USERNAME, "test");
        r2.fill(RequestField.PASSWORD, "test1234");
        RequestModel r3 = new RequestModel();
        r3.fill(RequestField.USER_ID, 1);
        r3.fill(RequestField.PASSWORD, "test1234");
        login.request(r2);
        User user = testUserRepository.findByUsername("test").get();
        assertTrue(user.getLoginStatus());
        logout.request(r3);
        assertFalse(user.getLoginStatus());
    }

}
