package com.api.wishlist.controller.request;

import lombok.Data;

@Data
public class AuthRequest {
	private String passKey;
	private String secretKey;
}