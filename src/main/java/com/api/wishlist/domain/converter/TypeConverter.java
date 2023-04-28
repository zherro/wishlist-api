package com.api.wishlist.domain.converter;

public interface TypeConverter {

    static int safeParseStringToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException | NullPointerException ignored) {}

        return 0;
    }

    static boolean parseBoolean(String value) {
        return Boolean.parseBoolean(value);
    }

}
