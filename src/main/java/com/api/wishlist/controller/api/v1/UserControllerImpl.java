package com.api.wishlist.controller.api.v1;

import com.api.wishlist.config.AppConfig;
import com.api.wishlist.controller.api.UserController;
import com.api.wishlist.controller.request.UserRegistryRequest;
import com.api.wishlist.domain.dto.UserRegistryDTO;
import com.api.wishlist.service.UserRegistryService;
import com.api.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Qualifier("UserController-V1")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class UserControllerImpl implements UserController {

    private final AppConfig appConfig;
    private final UserRegistryService registryService;
    private final WishlistService wishlistService;
    private final ModelMapper modelMapper;

    @Override
    public void userRegistry(UserRegistryRequest userRegistryRequest) {

        userRegistryRequest.validate(appConfig.getWishlistMaxSize(), appConfig.getValidateUserEmailPattern());

        log.info("m=userRegistry, receipting user data of userId:{}", userRegistryRequest.getUserId());

        var userDTO = modelMapper.map(userRegistryRequest, UserRegistryDTO.class);
        registryService.registry(userDTO);
        wishlistService.updateWishlist(userDTO);
    }

    @Override
    public UserRegistryDTO getUserWishlist(String userId) {
        var userData = registryService.findById(userId);
        userData.setWishList( wishlistService.listWishlistByUser(userId) );

        return userData;
    }

}
