package com.api.wishlist.controller.request;

import com.api.wishlist.config.exceptions.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import static com.api.wishlist.config.exceptions.ExceptionMessage.ERROR_EMAIL_VALIDATION_MESSAGE;
import static com.api.wishlist.config.exceptions.ExceptionMessage.ERROR_VALIDATION_MESSAGE;

/**
 *  User registry request
 */
@Getter
@Setter
public class UserRegistryRequest {

    private static final String REGEX_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private String userId;
    private String userName;
    private String userEmail;

    private List<WishItemRequest> wishList;

    /**
     * validate user request DTO
     *
     * @param wishListMaxSize - integer - value o max number of items accepted in wishList by user
     */
    public void validate(final int wishListMaxSize) {
        var isValid =  StringUtils.isNotEmpty(userId)
                && StringUtils.isNotEmpty(userName)
                && StringUtils.isNotEmpty(userEmail)
                && Optional.ofNullable(wishList).orElse(new ArrayList<>()).size() <= wishListMaxSize;

        if(!isValid) {
            throw new ValidationException(ERROR_VALIDATION_MESSAGE);
        }
    }

    /**
     * validate user request DTO and validate email pattern
     *
     * @param wishListMaxSize - integer - value o max number of items accepted in wishList by user
     * @param validateEmail - boolean
     *                      - TRUE: validate email patter
     *                      - FALSE: skip email patter validation
     */
    public void validate(final int wishListMaxSize, final boolean validateEmail) {
        validate(wishListMaxSize);

        var isValid = Pattern.compile(REGEX_PATTERN).matcher(userEmail).matches();
        if(validateEmail && !isValid) {
            throw new ValidationException(ERROR_EMAIL_VALIDATION_MESSAGE);
        }
    }
}
