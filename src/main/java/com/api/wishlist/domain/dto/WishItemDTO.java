package com.api.wishlist.domain.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishItemDTO {

    private String productId;
    private String description;
    private BigDecimal price;
}
