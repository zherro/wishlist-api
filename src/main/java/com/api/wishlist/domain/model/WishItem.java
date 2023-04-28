package com.api.wishlist.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *  Wish List item
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = WishItem.WISHLIST_COLLECTION_NAME)
public class WishItem {

    public static final String WISHLIST_COLLECTION_NAME = "cl_wish_item";

    @Id
    @EqualsAndHashCode.Include
    private String productId;

    private String userId;

    private String description;
    private BigDecimal price;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
