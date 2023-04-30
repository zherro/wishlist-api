package com.api.wishlist.controller.api;

import com.api.wishlist.config.AppConfig;
import com.api.wishlist.config.exceptions.BusinessException;
import com.api.wishlist.controller.api.v1.WishlistControllerImpl;
import com.api.wishlist.controller.api.v2.WishlistControllerV2Impl;
import com.api.wishlist.controller.request.ClearUserWishlistRequest;
import com.api.wishlist.controller.request.RemoveUserWishlistItemRequest;
import com.api.wishlist.domain.converter.WishlistConverter;
import com.api.wishlist.repository.WishlistRepository;
import com.api.wishlist.service.WishlistSearchService;
import com.api.wishlist.service.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class WishlistControllerImplTest {

    @Autowired
     WishlistRepository wishlistRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MongoOperations mongoOperations;

    WishlistController wishlistController;

    WishlistController wishlistControllerV2;

    @Container
    protected static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    @DynamicPropertySource
    protected static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeEach
    void setUp() {
        var modelMapper = new ModelMapper();
        var appConfig = new AppConfig("20", "false", "true");
        var wishlistService = new WishlistService(appConfig, wishlistRepository, new WishlistConverter(modelMapper));
        var wishlistSearchService = new WishlistSearchService(mongoTemplate);

        this.wishlistController = new WishlistControllerImpl(wishlistService, wishlistSearchService);
        this.wishlistControllerV2 = new WishlistControllerV2Impl(wishlistService, wishlistSearchService);
    }

    @Test
    @DisplayName("Validar paremtros")
    void searchByProductValidateParameter() {
        assertDoesNotThrow(() -> wishlistController.searchByProduct("","","", ""));
        assertDoesNotThrow(() -> wishlistController.searchByProduct("121","","", ""));
        assertDoesNotThrow(() -> wishlistController.searchByProduct("","1321","", ""));
        assertDoesNotThrow(() -> wishlistController.searchByProduct("sdfsf","1321","1", "0"));
        assertDoesNotThrow(() -> wishlistController.searchByProduct("","1321","1", "0"));
        assertDoesNotThrow(() -> wishlistController.searchByProduct("23242","1321","1", "0"));
        assertDoesNotThrow(() -> wishlistController.searchByProduct("2323","1321","1", "1"));
        assertDoesNotThrow(() -> wishlistController.searchByProduct("23242","1321","0", "0"));
        assertDoesNotThrow(() -> wishlistController.searchByProduct("23242","1321","fsdfs", "0"));
        assertDoesNotThrow(() -> wishlistController.searchByProduct("23242","1321","", "ewrwre"));
    }

    @Test
    @DisplayName("Validar paremtros")
    void searchTest() {
        var response = wishlistController.searchByProduct("","","", "");

        assertEquals(0, response.getTotalElements());
        assertEquals(0, response.getTotalPages());
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