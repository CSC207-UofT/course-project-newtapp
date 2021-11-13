package com.newts.newtapp.api.application;
import com.newts.newtapp.api.ConversationRepository;
import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.errors.*;
import com.newts.newtapp.api.application.user.*;
import com.newts.newtapp.entities.Conversation;
import org.springframework.context.annotation.Configuration;

/**
 * An object representing an UserManager of the application.
 */
@Configuration
public class UserManager {
    /**
     * The UserRepository this UserManager is working with.
     */
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;

    /**
     * Initialize a new UserManager with given UserRepository.
     * @param userRepository    UserRepository to access User data
     * @param conversationRepository ConversationRepository to access Conversation data
     */
    public UserManager(UserRepository userRepository, ConversationRepository conversationRepository) {
        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
    }

    /**
     * Returns a UserProfile given a User id in a RequestModel.
     * @param request           RequestModel containing User's id
     * @return                  UserProfile of corresponding User
     * @throws UserNotFound     if User id does not exist
     */
    public UserProfile getProfile(RequestModel request) throws UserNotFound {
        GetProfile getProfile = new GetProfile(userRepository);
        return getProfile.request(request);
    }

    /**
     * Creates a new user according to the given RequestModel and sets this UserManager's user accordingly.
     * @param request   RequestModel containing new User information.
     */
    public void createUser(RequestModel request) throws InvalidPassword, UserAlreadyExists {
        Create create = new Create(userRepository);
        create.request(request);
    }

    /**
     * Logs in a user according to RequestModel information and sets this UserManager's user accordingly.
     */
    public void login(RequestModel request) throws UserNotFound, IncorrectPassword {
        Login login = new Login(userRepository);
        login.request(request);
    }

    /**
     * Logs out a user.
     * @param request           RequestModel containing User's id
     * @throws UserNotFound     If user does not exist
     */
    public void logout(RequestModel request) throws UserNotFound {
        Logout logout = new Logout(userRepository);
        logout.request(request);
    }

    /**
     * Deletes a user according to the given RequestModel and sets this UserManager's user to null.
     * @param request   RequestModel containing delete User information.
     */
    public void delete(RequestModel request) throws UserNotFound, IncorrectPassword {
        Delete delete = new Delete(userRepository);
        delete.request(request);
    }

    /**
     * Adjust two given users such that the first follows the second.
     * @param request   RequestModel containing addFriend User information.
     */
    public void addFollow(RequestModel request) throws UserNotFound, SameUser {
        AddFollow addFollow = new AddFollow(userRepository);
        addFollow.request(request);
    }

    public Conversation[] getRelevantConversations(RequestModel request) throws UserNotFound {
        GetRelevantConversations getRelevantConversations = new GetRelevantConversations(userRepository,
                conversationRepository);
        return getRelevantConversations.request(request);
    }

}
