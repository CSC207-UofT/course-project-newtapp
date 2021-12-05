package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.application.datatransfer.ConversationProfile;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.api.gateways.TestConversationRepository;
import com.newts.newtapp.api.gateways.TestUserRepository;
import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class GetRevelantConversationsByFollowingTest {
    TestConversationRepository c;
    TestUserRepository u;
    User user;
    User userOne;
    User userTwo;
    Conversation conversationOne;
    Conversation conversationTwo;
    Conversation conversationThree;
    GetRelevantConversationsByFollowing g;

    @Before
    public void setUp() {
        c = new TestConversationRepository();
        u = new TestUserRepository();

        user = new User();
        ArrayList<Integer> following = new ArrayList<>();
        following.add(1);
        following.add(2);
        ArrayList<Integer> blockedUser = new ArrayList<>();
        ArrayList<String> interest = new ArrayList<>();
        interest.add("a");
        ArrayList<String> notInterest = new ArrayList<>();
        notInterest.add("b");
        user.setId(7);
        user.setInterests(interest);
        user.setLocation("Toronto");
        user.setBlockedUsers(blockedUser);
        user.setFollowing(following);

        conversationOne = new Conversation();
        conversationTwo = new Conversation();
        conversationThree = new Conversation();
        conversationOne.setId(1);
        conversationTwo.setId(2);
        conversationThree.setId(3);
        conversationOne.setTitle("a");
        conversationTwo.setTitle("a");
        conversationThree.setTitle("b");
        conversationOne.setTopics(interest);
        conversationTwo.setTopics(interest);
        conversationThree.setTopics(notInterest);
        conversationOne.setLocation("a");
        conversationTwo.setLocation("a");
        conversationThree.setLocation("b");
        conversationOne.setMaxSize(5);
        conversationTwo.setMaxSize(5);
        conversationThree.setMaxSize(5);
        ArrayList<Integer> conversationOneFollowing = new ArrayList<>();
        conversationOneFollowing.add(1);
        conversationOne.setUsers(conversationOneFollowing);
        ArrayList<Integer> conversationTwoFollowing = new ArrayList<>();
        conversationOneFollowing.add(2);
        conversationOne.setUsers(conversationTwoFollowing);
        ArrayList<Integer> conversationThreeFollowing = new ArrayList<>();
        conversationOneFollowing.add(4);
        conversationOne.setUsers(conversationThreeFollowing);

        userOne = new User();
        userOne.setId(1);
        ArrayList<Integer> userOneConversation = new ArrayList<>();
        conversationOneFollowing.add(1);
        userOne.setConversations(userOneConversation);

        userTwo = new User();
        userTwo.setId(2);
        ArrayList<Integer> userTwoConversation = new ArrayList<>();
        conversationTwoFollowing.add(2);
        userTwo.setConversations(userTwoConversation);

        c.save(conversationThree);
        c.save(conversationTwo);
        c.save(conversationOne);

        u.save(user);
        u.save(userOne);
        u.save(userTwo);

        g = new GetRelevantConversationsByFollowing(u, c);
    }

    @Test(timeout=50)
    public void testGetRelevantConversationsByFollowers() throws UserNotFound {
        RequestModel r = new RequestModel();

        r.fill(RequestField.USER_ID, 7);

        ArrayList<ConversationProfile> cp = g.request(r);

        Assert.assertEquals(2, cp.size());
    }
}


