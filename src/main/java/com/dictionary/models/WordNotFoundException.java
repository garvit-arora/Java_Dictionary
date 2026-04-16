package com.dictionary.models;


public class WordNotFoundException extends Exception {
    public WordNotFoundException(String message) {
        super(message);
    }
}
