package com.api.wishlist.service;

import com.api.wishlist.domain.model.WishItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class WishlistSearchService {

    private static final int DEFAULT_PAGE_SIZE = 20;

    private final MongoOperations mongoOperations;

    public Page<WishItem> searchWishlist(final String productId, final String userId, final int page, final int size) {

        Query query = new Query();
        var pageable = PageRequest.of(page, size <= 0 ? DEFAULT_PAGE_SIZE : size);

        if (StringUtils.isNotEmpty(productId)) {
            query.addCriteria(Criteria.where("productId").is(productId));
        }

        if (StringUtils.isNotEmpty(userId)) {
            query.addCriteria(Criteria.where("userId").is(userId));
        }

        var result = mongoOperations.find(query.with(pageable), WishItem.class);
        return PageableExecutionUtils.getPage(
                result,
                pageable,
                () -> mongoOperations.count(Query.of(query).limit(-1).skip(-1), WishItem.class));
    }
}
