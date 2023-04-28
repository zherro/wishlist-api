package com.api.wishlist.controller.request;

import com.api.wishlist.config.exceptions.BusinessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import static com.api.wishlist.config.AppConfig.MAX_ID_SIZE;
import static com.api.wishlist.config.exceptions.ExceptionMessage.ERROR_EMAIL_VALIDATION_MESSAGE;
import static com.api.wishlist.config.exceptions.ExceptionMessage.ERROR_MAX_WISHLIST_LIMIT_MESSAGE;
import static com.api.wishlist.config.exceptions.ExceptionMessage.ERROR_VALIDATION_MESSAGE;

/**
 *  User registry request
 */
@Builder
@Getter
@Setter
public class UserRegistryRequest {

    private static final String REGEX_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private String userId;
    private String userName;
    private String userEmail;

    public List<WishItemRequest> getWishList() {
        return Optional.ofNullable(wishList).orElse(new ArrayList<>());
    }

    private List<WishItemRequest> wishList;

    /**
     * validate user request DTO
     *
     * @param wishListMaxSize - integer - value o max number of items accepted in wishList by user
     */
    public void validate(final int wishListMaxSize) {
        var isValid =  StringUtils.isNotEmpty(userId)
                && StringUtils.isNotEmpty(userName)
                && StringUtils.isNotEmpty(userEmail);

        if(!isValid) {
            throw new BusinessException(ERROR_VALIDATION_MESSAGE);
        }

        if(getWishList().size() > wishListMaxSize) {
            throw new BusinessException(ERROR_MAX_WISHLIST_LIMIT_MESSAGE);
        }

        var hasAnyProductWithBigId = getWishList().stream()
                .anyMatch(wi -> wi.getProductId().length() > MAX_ID_SIZE);

        if(userId.length() > MAX_ID_SIZE || hasAnyProductWithBigId) {
            throw new BusinessException(ERROR_MAX_WISHLIST_LIMIT_MESSAGE);
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
            throw new BusinessException(ERROR_EMAIL_VALIDATION_MESSAGE);
        }
    }
}
