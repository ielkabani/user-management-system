package com.elkabani.db_demo.exceptions;

/**
 * Exception thrown when password validation fails
 */
public class WeakPasswordException extends RuntimeException {
    public WeakPasswordException(String message) {
        super(message);
    }
}

