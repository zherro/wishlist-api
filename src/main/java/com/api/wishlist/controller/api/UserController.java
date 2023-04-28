package com.api.wishlist.controller.api;

import com.api.wishlist.controller.request.UserRegistryRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface UserController {

    @ApiOperation(value = "Endpoit for registry user wishlist updates")
    @PostMapping("/registry")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void userRegistry(@RequestBody UserRegistryRequest userRegistryRequest);
}
