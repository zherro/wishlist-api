package com.api.wishlist.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *  User data
 */
@Data
@Document(collection = "user_wishlist")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @EqualsAndHashCode.Include
    private String userId;

    private String userName;
    private String userEmail;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private Set<WishItem> wishList;

    public boolean isNew() {
        return Objects.isNull(createdAt);
    }
}
