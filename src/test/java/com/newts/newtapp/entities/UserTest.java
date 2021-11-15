package com.newts.newtapp.entities;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {
    User u;

    /**
     * Setting up a test User
     */
    @Before
    public void setUp() {
        ArrayList<String> interests = new ArrayList<String>();
        interests.add("Sports");
        interests.add("Music");
        interests.add("Fitness");
        u = new User(0, "testUser", "password123", interests);

        u.logIn();
    }

    /**
     * To be implemented
     */
    @Test
    public void isLoggedIn() {
        assertTrue(false);
    }

    /**
     * Tests the getID method
     */
    @Test
    public void testGetId() {
        assertEquals(u.getId(), 1);
    }

    /**
     * Tests getUsername method
     */
    @Test
    public void testGetUsername() {assertEquals(u.getUsername(), "test_user");}

    /**
     * Tests getSetUsername method
     */
    @Test
    public void testSetUsername() {
        u.setUsername("OptimisticNewt27");
        assertEquals(u.getUsername(), "OptimisticNewt27");
    }

    /**
     * Tests getPassword method
     */
    @Test
    public void testGetPassword() {assertEquals(u.getPassword(), "password123");}

    @Test
    public void testSetPassword() throws EntityExceptions {
        u.setPassword("p");
    }

    /**
     * To be implemented
     */
    @Test
    public void testGetLocation() {
        assertTrue(false);
    }

    /**
     * Tests getInterests method
     */
    @Test
    public void testGetInterests() {
        assertEquals(u.getInterests().get(0), "Sports");
        assertEquals(u.getInterests().get(1), "Music");
        assertEquals(u.getInterests().get(2), "Fitness");
    }

    /**
     * Tests addInterests method
     */
    @Test
    public void addInterests() {
        u.addInterests("Stocks");
        assertEquals(u.getInterests().get(3), "Stocks");
    }

    /**
     * Tests removeInterest method
     */
    @Test
    public void removeInterest() {
        u.addInterests("Stocks");
        assertEquals(u.getInterests().get(2), "Fitness");
    }

    /**
     * Tests getLoginStatus method
     */
    @Test
    public void testGetLoginStatus() {
        assertEquals(u.getLoginStatus(), true);
    }


    /**
     * Tests logOut method
     */
    @Test
    public void testLogOut() {
        u.logOut();
        assertEquals(u.getLoginStatus(), false);
    }

    /**
     * Tests logIn method
     */
    @Test
    public void testLogIn() {
        u.logIn();
        assertEquals(u.getLoginStatus(), true);
    }


}
