package com.api.wishlist.repository;

import com.api.wishlist.domain.model.WishItem;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WishlistRepository extends MongoRepository<WishItem, String> {

    Optional<Set<WishItem>> findAllByUserId(String userId);

    void deleteByProductIdAndUserId(String wishlistItemId, String userId);

    void deleteAllByUserId(String userId);

    Page<WishItem> findAllByProductId(String productId, Pageable pageable);
}
