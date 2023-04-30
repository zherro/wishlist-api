package com.api.wishlist.controller.api;

import com.api.wishlist.builder.UserRegistryRequestBuilder;
import com.api.wishlist.config.AppConfig;
import com.api.wishlist.config.exceptions.BusinessException;
import com.api.wishlist.controller.api.v1.UserControllerImpl;
import com.api.wishlist.controller.api.v2.UserControllerV2Impl;
import com.api.wishlist.controller.request.UserRegistryRequest;
import com.api.wishlist.domain.converter.UserConverter;
import com.api.wishlist.domain.converter.WishlistConverter;
import com.api.wishlist.repository.UserRepository;
import com.api.wishlist.repository.WishlistRepository;
import com.api.wishlist.service.UserRegistryService;
import com.api.wishlist.service.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Testcontainers
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class UserControllerImplTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    WishlistRepository wishlistRepository;

    private UserController userController;

    private UserController userControllerV2;

    @Container
    protected static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    @DynamicPropertySource
    protected static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeEach
    void setUp() {
        mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
        var appConfig = new AppConfig("20", "false", "true");
        var modelMapper = new ModelMapper();
        var userService = new UserRegistryService(userRepository, new UserConverter(modelMapper),mongoTemplate);
        var wishListService = new WishlistService(appConfig, wishlistRepository, new WishlistConverter(modelMapper));

        this.userController = new UserControllerImpl(appConfig, userService, wishListService, modelMapper);
        this.userControllerV2 = new UserControllerV2Impl(appConfig, userService, wishListService, modelMapper);
    }

    @Test
    @DisplayName("Registra usuario e atualiza wishlist")
    void givenValidPayload_shouldValidateAndProcessUserData() {
        var userRequest = new UserRegistryRequestBuilder().generateMock();
        assertDoesNotThrow(() -> userController.userRegistry(userRequest));
        assertDoesNotThrow(() -> userControllerV2.userRegistry(userRequest));
    }

    @Test
    @DisplayName("Gera exception ao receber payload invalido")
    void givenInvalidPayload_shouldValidateAndReturnException() {
        final var userRequest = new UserRegistryRequestBuilder().generateMock();

        userRequest.setUserEmail(null);
        doThrowBussinesException(userRequest);

        userRequest.setUserEmail("email@email.com");
        userRequest.setUserName("");
        doThrowBussinesException(userRequest);

        userRequest.setUserId("");
        userRequest.setUserName("um nome");
        doThrowBussinesException(userRequest);
    }

    private void doThrowBussinesException(UserRegistryRequest userRequest) {
        assertThrows(BusinessException.class, () -> userController.userRegistry(userRequest));
        assertThrows(BusinessException.class, () -> userControllerV2.userRegistry(userRequest));
    }

}