package com.newts.newtapp.api.application;

import java.util.HashMap;
import java.util.Map;

/**
 * A data structure representing a response passed through an OutputBoundary Interface.
 */
public class ResponseModel {
    /**
     * A Map containing the response's data. Specific Key-Value contracts are not enforced here.
     * See ResponseField enum for valid keys.
     */
    private final Map<ResponseField, Object> data;

    /**
     * Construct a new empty ResponseModel.
     */
    public ResponseModel() {
        data = new HashMap<>();
    }

    /**
     * Sets the value at specified key to the specified value in this ResponseModel.
     * @param key       Key to store value at.
     * @param value     Value to store at given key.
     */
    public void fill(ResponseField key, Object value) {
        data.put(key, value);
    }

    /**
     * @param key   Key to fetch data from.
     * @return      Value at input key in this ResponseModel or null if key does not have any value.
     */
    public Object get(ResponseField key) {
        return data.get(key);
    }
}
