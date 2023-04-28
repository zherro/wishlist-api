package com.api.wishlist.controller.api;

import com.api.wishlist.controller.request.ClearUserWishlistRequest;
import com.api.wishlist.controller.request.RemoveUserWishlistItemRequest;
import com.api.wishlist.domain.model.WishItem;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface WishlistController {

    @ApiOperation(value = "Endpoit for search wishlist")
    @PatchMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    Page<WishItem> searchByProduct( @RequestParam(required = false) String productId,
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) String page);

    @ApiOperation(value = "Endpoit for REMOVE specific item of user wishlist")
    @PatchMapping("/remove-item")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void removeUserWishlistItem(@RequestBody RemoveUserWishlistItemRequest removeItemRequest);


    @ApiOperation(value = "Endpoit for CLEAR user wishlist")
    @PutMapping("/clear-user-wishlist")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void clearUserWishlist(@RequestBody ClearUserWishlistRequest clearWishlistRequest);
}
