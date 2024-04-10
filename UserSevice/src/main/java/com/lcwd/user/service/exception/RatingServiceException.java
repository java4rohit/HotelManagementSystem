package com.lcwd.user.service.exception;

public class RatingServiceException extends RuntimeException {

    public RatingServiceException(String message) {
        super(message);
    }

    public RatingServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}