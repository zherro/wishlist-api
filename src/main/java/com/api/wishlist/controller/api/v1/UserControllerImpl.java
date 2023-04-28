package com.api.wishlist.controller.api.v1;

import com.api.wishlist.config.AppConfig;
import com.api.wishlist.controller.api.UserController;
import com.api.wishlist.controller.request.UserRegistryRequest;
import com.api.wishlist.domain.dto.UserRegistryDTO;
import com.api.wishlist.service.UserRegistryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class UserControllerImpl implements UserController {

    private final AppConfig appConfig;
    private final UserRegistryService registryService;
    private final ModelMapper modelMapper;

    @Override
    public void userRegistry(UserRegistryRequest userRegistryRequest) {
            userRegistryRequest.validate(appConfig.getWishlistMaxSize(), appConfig.getValidateUserEmailPattern());

            log.info("m=userRegistry, receipting user data for userId:{}", userRegistryRequest.getUserId());
            registryService.registry(modelMapper.map(userRegistryRequest, UserRegistryDTO.class));
    }
}
