package com.api.wishlist.config.exceptions;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(ExceptionMessage message) {
        this(message.getMessage());
    }
}
