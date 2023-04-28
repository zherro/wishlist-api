package com.api.wishlist.builder;

import com.api.wishlist.controller.request.UserRegistryRequest;
import com.api.wishlist.controller.request.WishItemRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class UserRegistryRequestBuilder  {

    private final UserRegistryRequest.UserRegistryRequestBuilder userRegistryDTO = UserRegistryRequest.builder();

    public UserRegistryRequest generateMock() {
        userRegistryDTO.userId(UUID.randomUUID().toString());
        userRegistryDTO.userName("user name");
        userRegistryDTO.userEmail("email@email.com");

        var result = userRegistryDTO.build();
        result.getWishList().addAll(List.of(
                WishItemRequest.builder().productId(UUID.randomUUID().toString()).price(new BigDecimal(10)).build(),
                WishItemRequest.builder().productId(UUID.randomUUID().toString()).description("um produto").build()
        ));

        return result;
    }

}
