package com.api.wishlist.config.jwt;

import com.api.wishlist.service.AuthImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtUtils {

	@Value("${api.wishlist.jwtSecret}")
	private String jwtSecret;

	@Value("${api.wishlist.jwtExpirationMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(Authentication authentication) {

		AuthImpl userPrincipal = (AuthImpl) authentication.getPrincipal();

		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			log.error("m=validateJwtToken, Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("m=validateJwtToken, Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("m=validateJwtToken, JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("m=validateJwtToken, JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("m=validateJwtToken, JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}