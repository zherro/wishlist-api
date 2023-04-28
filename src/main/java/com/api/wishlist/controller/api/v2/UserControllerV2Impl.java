package com.api.wishlist.controller.api.v2;

import com.api.wishlist.config.AppConfig;
import com.api.wishlist.controller.api.v1.UserControllerImpl;
import com.api.wishlist.service.UserRegistryService;
import com.api.wishlist.service.WishlistService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Qualifier("UserControllerImpl-V2")
@RestController
@RequestMapping("api/v2")
public class UserControllerV2Impl extends UserControllerImpl  {

    public UserControllerV2Impl(AppConfig appConfig, UserRegistryService registryService,
            WishlistService wishlistService, ModelMapper modelMapper) {
        super(appConfig, registryService, wishlistService, modelMapper);
    }
}
