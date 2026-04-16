package com.dictionary.models;

/**
 * User-defined exception as per Unit II of syllabus.
 */
public class WordNotFoundException extends Exception {
    public WordNotFoundException(String message) {
        super(message);
    }
}
