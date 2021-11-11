package com.newts.newtapp.application;

import java.util.HashMap;
import java.util.Map;

/**
 * A data structure representing a request passed through an InputBoundary Interface.
 */
public class RequestModel {
    /**
     * Location to send output to during a request involving this RequestModel.
     */
    private final OutputBoundary output;

    /**
     * A Map containing the request's data. Specific Key-Value contracts are not enforced here, this should be
     * documented by each class that wants to use a RequestModel. See RequestField enum for valid keys.
     */
    private final Map<RequestField, Object> data;

    /**
     * Create an empty RequestModel.
     */
    public RequestModel(OutputBoundary output) {
        this.output = output;
        this.data = new HashMap<>();
    }

    /**
     * Sets the value at specified key to the specified value in this RequestModel.
     * @param key       Key to store value at.
     * @param value     Value to store at given key.
     */
    public void fill(RequestField key, Object value) {
        data.put(key, value);
    }

    /**
     * @param key   Key to fetch data from.
     * @return      Value at input key in this RequestModel or null if key does not have any value.
     */
    public Object get(RequestField key) {
        return data.get(key);
    }

    /**
     * @return  The OutputBoundary associated with this RequestModel.
     */
    public OutputBoundary getOutput() {
        return output;
    }
}