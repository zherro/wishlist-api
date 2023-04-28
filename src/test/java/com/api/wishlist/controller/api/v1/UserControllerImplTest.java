package com.api.wishlist.controller.api.v1;

import com.api.wishlist.SpringTestContex;
import com.api.wishlist.builder.UserRegistryRequestBuilder;
import com.api.wishlist.config.exceptions.BusinessException;
import com.api.wishlist.controller.api.UserController;
import com.api.wishlist.domain.model.User;
import com.api.wishlist.repository.UserRepository;
import com.api.wishlist.repository.WishlistRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserControllerImplTest extends SpringTestContex {

    @Autowired
    @Qualifier("UserController-V1")
    private UserController userController;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private WishlistRepository wishlistRepository;

    @Test
    @DisplayName("Registra usuario e atualiza wishlist")
    void givenValidPayload_shouldValidateAndProcessUserData() {
        var userRequest = new UserRegistryRequestBuilder().generateMock();
        when(userRepository.save(any())).thenReturn((new ModelMapper()).map(userRequest, User.class));
        assertDoesNotThrow(() -> userController.userRegistry(userRequest));
    }

    @Test
    @DisplayName("Gera exception ao receber payload invalido")
    void givenInvalidPayload_shouldValidateAndReturnException() {
        var userRequest = new UserRegistryRequestBuilder().generateMock();
        when(userRepository.save(any())).thenReturn((new ModelMapper()).map(userRequest, User.class));

        userRequest.setUserEmail(null);
        assertThrows(BusinessException.class, () -> userController.userRegistry(userRequest));


        userRequest.setUserEmail("email@email.com");
        userRequest.setUserName("");
        assertThrows(BusinessException.class, () -> userController.userRegistry(userRequest));

        userRequest.setUserId("");
        userRequest.setUserName("um nome");
        assertThrows(BusinessException.class, () -> userController.userRegistry(userRequest));
    }

}