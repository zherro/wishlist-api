package com.api.wishlist.controller.request;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class WishItemRequest {

    private String productId;
    private String description;
    private BigDecimal price;
}
