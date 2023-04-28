package com.api.wishlist.controller.api.v2;

import com.api.wishlist.controller.api.v1.WishlistControllerImpl;
import com.api.wishlist.service.WishlistSearchService;
import com.api.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Qualifier("WishlistController-V2")
@RestController
@RequestMapping("api/v2")
public class WishlistControllerV2Impl extends WishlistControllerImpl {

    public WishlistControllerV2Impl(
            WishlistService wishlistService, WishlistSearchService wishlistSearchService) {
        super(wishlistService, wishlistSearchService);
    }

}
