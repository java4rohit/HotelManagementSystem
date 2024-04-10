package com.lcwd.hotel.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Resource Not Found On Server !!");
    }

    public ResourceNotFoundException(String mgs) {
        super(mgs);
    }

}
