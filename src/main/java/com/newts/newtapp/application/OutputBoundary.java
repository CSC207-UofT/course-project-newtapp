package com.newts.newtapp.application;

/**
 * An Output Boundary interface. Defines a standard output method by which to pass ResponseModel objects.
 */
public interface OutputBoundary {

    /**
     * Accepts a response.
     * @param response   a response stored as a ResponseModel
     */
    public void respond(ResponseModel response);
}
