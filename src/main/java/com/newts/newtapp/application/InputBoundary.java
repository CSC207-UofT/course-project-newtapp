package com.newts.newtapp.application;

/**
 * An Input Boundary interface. Defines a standard input method by which to pass RequestModel objects.
 */
public interface InputBoundary {

    /**
     * Accepts a request.
     * @param request   a request stored as a RequestModel
     */
    public void request(RequestModel request);
}