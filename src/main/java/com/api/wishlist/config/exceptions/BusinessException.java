package com.api.wishlist.config.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(ExceptionMessage message) {
        this(message.getMessage());
    }
}
