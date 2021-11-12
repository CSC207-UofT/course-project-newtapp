package com.newts.newtapp.application;

import com.newts.newtapp.entities.Conversation;
import com.newts.newtapp.entities.Message;
import com.newts.newtapp.entities.User;

import java.util.ArrayList;
import java.util.Objects;

public class DataBase {

    private static final ArrayList<User> UserList = new ArrayList<>();
    private static final ArrayList<Conversation> ConversationList = new ArrayList<>();
    private static final ArrayList<Message> MessageList = new ArrayList<>();

    /**
     * add a Conversation to ConversationList
     * @param user Conversation to be added
     */
    public static void addUser(User user){
        UserList.add(user);
    }

    public static User getUser(String username) {
        for (User user : UserList) {
            if (Objects.equals(user.getUsername(), username)) {
                return user;
            }
        }
        return new User();
    }

    /**
     *
     * @param id ID corresponding to a user
     * @return True if there exists a user corresponding to the required id, False otherwise
     */
    public static Boolean containsUserID(int id)  {
        for (User user : UserList) {
            if (user.getId() == id) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public static void deleteUser(int id) {
        UserList.removeIf(user -> user.getId() == id);
    }

    /**
     * add a Conversation to ConversationList
     * @param conversation Conversation to be added
     */
    public static void addConversation(Conversation conversation){
        ConversationList.add(conversation);
    }

    /**
     * return the ConversationList
     * @return  returns ConversationList
     */
    public static ArrayList<Conversation> getConversationList(){
        return new ArrayList<>(ConversationList);
    }

    /**
     * return the conversation with the given id
     * @param id the id of the conversation
     * @return the conversation
     */
    public static Conversation getConversation(String id){
        for (Conversation conversation: ConversationList){
            if (Objects.equals(conversation.getId(), id)) {
                return conversation;
            }
        }
        return new Conversation();
    }

    /**
     * Adds message to specified conversation's messagelist.
     * @param conversationID String id of the conversation that the message is being added to.
     * @param message Message being added.
     */
    public static void addMessage(int conversationID, Message message){
        for(Conversation c : ConversationList){
            if(Objects.equals(c.getId(), conversationID)){
                c.addMessage(message);
            }
        }
    }


}
