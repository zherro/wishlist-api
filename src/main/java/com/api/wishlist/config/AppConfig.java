package com.api.wishlist.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.api.wishlist.domain.converter.TypeConverter.parseBoolean;
import static com.api.wishlist.domain.converter.TypeConverter.safeParseStringToInt;

@Component
public class AppConfig {

    public static final int MAX_ID_SIZE = 50;

    private final String wishlistMaxSize;

    private final String validateUserEmailPattern;

    private final String requiredUserEmailAndName;

    public AppConfig(@Value("${api.wishlist.wishlistMaxSize}") String wishlistMaxSize,
            @Value("${api.wishlist.validateUserEmailPattern}") String validateUserEmailPattern,
            @Value("${api.wishlist.requiredUserEmailAndName}")String requiredUserEmailAndName) {
        this.wishlistMaxSize = wishlistMaxSize;
        this.validateUserEmailPattern = validateUserEmailPattern;
        this.requiredUserEmailAndName = requiredUserEmailAndName;
    }

    public Integer getWishlistMaxSize() {
        return safeParseStringToInt(wishlistMaxSize);
    }

    public Boolean getValidateUserEmailPattern() {
        return parseBoolean(validateUserEmailPattern);
    }

    public Boolean getRequiredUserEmailAndName() {
        return parseBoolean(requiredUserEmailAndName);
    }
}
