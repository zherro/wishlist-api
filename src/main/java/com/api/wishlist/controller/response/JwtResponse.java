package com.api.wishlist.controller.response;

import lombok.Data;

@Data
public class JwtResponse {
  private String accessToken;
  private String tokenType = "Bearer";
  private String id;

  public JwtResponse(String accessToken, String id) {
    this.accessToken = accessToken;
    this.id = id;
  }
}