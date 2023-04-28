package com.api.wishlist.controller.api.v1;

import com.api.wishlist.controller.api.WishlistController;
import com.api.wishlist.controller.request.ClearUserWishlistRequest;
import com.api.wishlist.controller.request.RemoveUserWishlistItemRequest;
import com.api.wishlist.domain.model.WishItem;
import com.api.wishlist.service.WishlistSearchService;
import com.api.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.api.wishlist.domain.converter.TypeConverter.safeParseStringToInt;

@Slf4j
@Qualifier("WishlistController-V1")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class WishlistControllerImpl implements WishlistController {

    private final WishlistService wishlistService;
    private final WishlistSearchService wishlistSearchService;

    @Override
    public Page<WishItem> searchByProduct(String productId, String userId, String page, String size) {
        return wishlistSearchService.searchWishlist(
                productId, userId, safeParseStringToInt(page), safeParseStringToInt(size));
    }

    @Override
    public void removeUserWishlistItem(RemoveUserWishlistItemRequest removeItemRequest) {
        removeItemRequest.validate();

        log.info("m=RemoveUserItem, receipting user data of userId:{}", removeItemRequest.getUserId());
        wishlistService.removeUserItem(removeItemRequest.getUserId(), removeItemRequest.getProductId());
    }

    @Override
    public void clearUserWishlist(ClearUserWishlistRequest clearWishlistRequest) {
        clearWishlistRequest.validate();

        log.info("m=clearUserWishlist, receipting user data of userId:{}", clearWishlistRequest.getUserId());
        wishlistService.clearWishlistByUser(clearWishlistRequest.getUserId());
    }
}
