package com.api.wishlist.config.jwt;

import com.api.wishlist.config.exceptions.ApiExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		log.error("Unauthorized error: {}", authException.getMessage());

		var responseBody = new ApiExceptionHandler.ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write(new ObjectMapper().writeValueAsString(responseBody));
		response.getWriter().flush();
	}

}
