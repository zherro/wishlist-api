package com.api.wishlist.controller.api;

import com.api.wishlist.controller.request.UserRegistryRequest;
import com.api.wishlist.domain.dto.UserRegistryDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface UserController {

    @ApiOperation(value = "Endpoit for registry user wishlist updates")
    @PostMapping("/registry")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void userRegistry(@RequestBody UserRegistryRequest userRegistryRequest);

    @ApiOperation(value = "Endpoit retrieve user wishlist")
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    UserRegistryDTO getUserWishlist(@PathVariable String userId);
}
