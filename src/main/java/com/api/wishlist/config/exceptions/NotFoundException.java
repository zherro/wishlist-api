package com.api.wishlist.config.exceptions;

public class NotFoundException  extends BusinessException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(ExceptionMessage message) {
        this(message.getMessage());
    }
}
