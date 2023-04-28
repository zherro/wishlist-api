package com.api.wishlist.config;

import com.api.wishlist.domain.converter.TypeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.api.wishlist.domain.converter.TypeConverter.parseBoolean;
import static com.api.wishlist.domain.converter.TypeConverter.safeParseStringToInt;

@Component
public class AppConfig {

    public static final int MAX_ID_SIZE = 50;

    @Value("${api.wishlist.wishlistMaxSize}")
    private String wishlistMaxSize;

    @Value("${api.wishlist.validateUserEmailPattern}")
    private String validateUserEmailPattern;

    public Integer getWishlistMaxSize() {
        return safeParseStringToInt(wishlistMaxSize);
    }

    public Boolean getValidateUserEmailPattern() {
        return parseBoolean(validateUserEmailPattern);
    }
}
