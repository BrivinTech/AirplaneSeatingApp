package com.airplane.booking.app.exception;

/**
 * Custom exception class for seats not available issue.
 */
public class SeatsNotAvailableException extends  Exception{

    public SeatsNotAvailableException(String message) {
        super(message);
    }
}
