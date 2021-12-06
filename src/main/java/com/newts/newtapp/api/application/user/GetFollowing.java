package com.newts.newtapp.api.application.user;

import com.newts.newtapp.api.application.boundary.RequestField;
import com.newts.newtapp.api.application.boundary.RequestModel;
import com.newts.newtapp.api.errors.UserNotFound;
import com.newts.newtapp.api.gateways.UserRepository;
import com.newts.newtapp.entities.User;

import java.util.ArrayList;

public class GetFollowing extends UserInteractor<ArrayList<Integer>, Exception> {

    /**
     * Creating GetFollowers UserInteractor which returns an ArrayList of the User's Following ids
     * @param userRepository UserRepository for User data access
     */
    public GetFollowing(UserRepository userRepository){
        super(userRepository);
    }

    /**
     * Accepts a request.
     *
     * @param request a request stored as a RequestModel
     */
    @Override
    public ArrayList<Integer> request(RequestModel request) throws UserNotFound {
        int userId = (int) request.get(RequestField.USER_ID);
        User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);
        return user.getFollowing();
    }
}
