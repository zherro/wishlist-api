package com.api.wishlist.config.exceptions;

public class BlockAccessException extends RuntimeException {

    public BlockAccessException() {
        super("Access validation failure");
    }
}
