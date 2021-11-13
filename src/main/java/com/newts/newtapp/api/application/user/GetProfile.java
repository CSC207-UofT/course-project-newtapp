package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.RequestField;
import com.newts.newtapp.api.application.RequestModel;
import com.newts.newtapp.api.application.UserProfile;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.entities.User;

/**
 * Get a UserProfile for a given User.
 */
public class GetProfile extends UserInteractor<UserProfile, UserNotFound>{
    private UserRepository repository;

    /**
     * Construct a new GetProfile interactor with given UserRepository.
     * @param repository    UserRepository with user data
     */
    public GetProfile(UserRepository repository) {
        super(repository);
    }

    /**
     * Handles a GetProfile request.
     * @param request           a request stored as a RequestModel
     * @return                  a new UserProfile for the given User
     * @throws UserNotFound     if user does not exist
     */
    @Override
    public UserProfile request(RequestModel request) throws UserNotFound {
        int id = (int) request.get(RequestField.ID);
        User user = repository.findById(id).orElseThrow(UserNotFound::new);
        return new UserProfile(user.getId(), user.getUsername(), user.getLocation(),
                user.getInterests(), user.getLoginStatus(), user.getFollowing(), user.getFollowers(),
                user.getConversations());
    }
}