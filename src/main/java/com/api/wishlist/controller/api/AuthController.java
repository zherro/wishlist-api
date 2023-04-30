package com.api.wishlist.controller.api;

import com.api.wishlist.controller.request.CreateTokenRequest;
import com.api.wishlist.controller.request.GenerateBearerTokenRequest;
import com.api.wishlist.controller.response.CreateUserResponse;
import com.api.wishlist.controller.response.JwtResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface AuthController  {

    @ApiOperation(value = "Register and get authentication key")
    @PostMapping("/auth/create-passkey")
    @ResponseStatus(HttpStatus.OK)
    CreateUserResponse createUser(@RequestBody CreateTokenRequest name);

    @ApiOperation(value = "Endpoint para obter Bearer token, aqui utiliza a authentication key")
    @PostMapping("/auth/signin")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<JwtResponse> authenticateConsumer(@RequestBody GenerateBearerTokenRequest passKey);

}
