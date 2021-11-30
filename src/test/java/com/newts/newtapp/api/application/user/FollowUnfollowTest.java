package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.application.RequestField;
import com.newts.newtapp.api.application.RequestModel;
import com.newts.newtapp.api.errors.*;
import com.newts.newtapp.api.gateways.TestUserRepository;
import com.newts.newtapp.entities.User;
import org.junit.Before;
import org.junit.Test;
import com.newts.newtapp.api.application.user.Unfollow;

import static org.junit.Assert.*;
public class FollowUnfollowTest {
    TestUserRepository testUserRepository;
    Create create;
    Follow follow;
    Unfollow unfollow;

    @Before
    public void setUp() throws UserNotFound, SameUser, AlreadyFollowingUser, InvalidUsername, UserAlreadyExists,
            InvalidPassword {
        testUserRepository = new TestUserRepository();
        create = new com.newts.newtapp.api.application.user.Create(testUserRepository);
        follow = new com.newts.newtapp.api.application.user.Follow(testUserRepository);
        unfollow = new com.newts.newtapp.api.application.user.Unfollow(testUserRepository);
        RequestModel r = new RequestModel();
        r.fill(RequestField.USERNAME, "test");
        r.fill(RequestField.PASSWORD, "test123");
        r.fill(RequestField.INTEREST, "tests");
        create.request(r);
        RequestModel r2 = new RequestModel();
        r2.fill(RequestField.USERNAME, "test2");
        r2.fill(RequestField.PASSWORD, "test123");
        r2.fill(RequestField.INTEREST, "tests");
        create.request(r2);
    }

    @Test(timeout = 500)
    public void testFollow() throws UserNotFound, SameUser, AlreadyFollowingUser{
        RequestModel r3 = new RequestModel();
        User user1 = testUserRepository.findById(1).get();
        User user2 = testUserRepository.findById(2).get();
        r3.fill(RequestField.USER_ID, 1);
        r3.fill(RequestField.USER_ID_TWO, 2);
        follow.request(r3);
        assertTrue(user1.getFollowing().contains(2));
        assertTrue(user2.getFollowers().contains(1));
        unfollow.request(r3);
        assertFalse(user1.getFollowing().contains(2));
        assertFalse(user2.getFollowers().contains(1));
    }
}
