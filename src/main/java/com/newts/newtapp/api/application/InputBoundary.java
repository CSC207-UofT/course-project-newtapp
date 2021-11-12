package com.newts.newtapp.api.application;

import com.newts.newtapp.api.errors.InvalidPassword;
import com.newts.newtapp.api.errors.UserNotFound;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * An Input Boundary interface. Defines a standard input method by which to pass RequestModel objects.
 */
public interface InputBoundary<ReturnType, ExceptionType extends Exception> {

    /**
     * Accepts a request.
     * @param request   a request stored as a RequestModel
     */
    ReturnType request(RequestModel request) throws ExceptionType, UserNotFound, InvalidPassword;
}