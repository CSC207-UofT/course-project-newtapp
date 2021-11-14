package com.newts.newtapp.api.application;

/**
 * An Input Boundary interface. Defines a standard input method by which to pass RequestModel objects.
 */
public interface InputBoundary<ReturnType, ExceptionType extends Exception> {

    /**
     * Accepts a request.
     * @param request   a request stored as a RequestModel
     */
    ReturnType request(RequestModel request) throws ExceptionType;
}