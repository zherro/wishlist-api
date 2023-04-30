package com.api.wishlist.controller.api;

import com.api.wishlist.SpringTestContext;
import com.api.wishlist.config.exceptions.BusinessException;
import com.api.wishlist.controller.request.ClearUserWishlistRequest;
import com.api.wishlist.controller.request.RemoveUserWishlistItemRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WishlistControllerImplTest extends SpringTestContext {

    @Autowired
    @Qualifier("WishlistController-V1")
    private WishlistController wishlistController;

    @Autowired
    @Qualifier("WishlistController-V2")
    private WishlistController wishlistControllerV2;


    @Test
    @DisplayName("Registra usuario e atualiza wishlist")
    void searchByProduct() {
    }

    @Test
    @DisplayName("Remover item especifico da wishlist")
    void removeUserWishlistItem() {
        assertDoesNotThrow(() -> {
            var build = RemoveUserWishlistItemRequest.builder().userId("any-user-id").productId("any-prouct-id").build();
            wishlistController.removeUserWishlistItem(build);
            wishlistControllerV2.removeUserWishlistItem(build);
        });
    }

    @Test
    @DisplayName("Retorna exception ao tentar remover item com parametros invalidos")
    void whenAttemptRemoveUserWishlistItem_givenInvalidPayload_shouldThrowException() {
        assertThrows(BusinessException.class, () -> {
            var build = RemoveUserWishlistItemRequest.builder().userId(null).productId("").build();
            wishlistController.removeUserWishlistItem(build);
            wishlistControllerV2.removeUserWishlistItem(build);
        });
    }

    @Test
    @DisplayName("Limpa wishlist do user")
    void clearUserWishlist() {
        assertDoesNotThrow(() -> {
            var build = ClearUserWishlistRequest.builder().userId("any-user-id").build();
            wishlistController.clearUserWishlist(build);
            wishlistControllerV2.clearUserWishlist(build);
        });
    }

    @Test
    @DisplayName("Retorna exception ao tentar remover item com parametros invalidos")
    void whenAttemptClearUserWishlist_givenInvalidPayload_shouldThrowException() {
        assertThrows(BusinessException.class, () -> {
            var build = ClearUserWishlistRequest.builder().userId("").build();
            wishlistController.clearUserWishlist(build);
            wishlistControllerV2.clearUserWishlist(build);
        });
    }
}