package com.newts.newtapp.api.application;

public interface ConfigReader {

    /**
     * Return the string with the given key.
     * @param key The key of the string
     * @return The string
     */
    String get(String key);
}
