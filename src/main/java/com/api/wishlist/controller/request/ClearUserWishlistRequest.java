package com.api.wishlist.controller.request;

import com.api.wishlist.config.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import static com.api.wishlist.config.exceptions.ExceptionMessage.ERROR_VALIDATION_MESSAGE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClearUserWishlistRequest {

    private String userId;

    public void validate() {
        if(!StringUtils.isNotEmpty(userId)) {
            throw new BusinessException(ERROR_VALIDATION_MESSAGE);
        }
    }
}
