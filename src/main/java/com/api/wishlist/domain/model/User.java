package com.api.wishlist.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
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
@Document(collection = User.USER_COLLECTION_NAME)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    public static final String USER_COLLECTION_NAME = "cl_user_wishlist";

    @Id
    @EqualsAndHashCode.Include
    private String userId;

    private String userName;
    private String userEmail;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    /**
     * verifica se usuario ja foi persistido na base alguma vez
     */
    public boolean isNew() {
        return Objects.isNull(createdAt);
    }

    /**
     * retorna data de registro
     */
    public LocalDateTime getCreatedAt() {
        return Optional.ofNullable(createdAt).orElse(LocalDateTime.now());
    }
}
