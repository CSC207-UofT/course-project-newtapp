package com.newts.newtapp.api.application.user;
import com.newts.newtapp.api.UserRepository;
import com.newts.newtapp.api.application.InputBoundary;
import com.newts.newtapp.entities.User;

/**
 * An abstract UserInteractor object. Generally to be extended as a specific User usecase.
 * Requires simply that an implementing class stores a User object and handles requests.
 */
public abstract class UserInteractor<ReturnType, ExceptionType extends Exception>
        implements InputBoundary<ReturnType, ExceptionType> {
    private User user;
    private UserRepository repository;

    /**
     * Initialize a new UserInteractor with given repository.
     * @param repository    UserRepository containing user data.
     */
    public UserInteractor(UserRepository repository) {
        this.repository = repository;
    }
}
