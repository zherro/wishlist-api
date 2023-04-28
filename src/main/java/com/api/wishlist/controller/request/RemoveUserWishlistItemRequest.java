package com.api.wishlist.controller.request;

import com.api.wishlist.config.exceptions.BusinessException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import static com.api.wishlist.config.exceptions.ExceptionMessage.ERROR_VALIDATION_MESSAGE;

@Builder
@Getter
@Setter
public class RemoveUserWishlistItemRequest {

    private String userId;
    private String productId;

    public void validate() {
        var isValid = StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(productId);

        if(!isValid) {
            throw new BusinessException(ERROR_VALIDATION_MESSAGE);
        }
    }
}
