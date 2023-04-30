package com.api.wishlist.controller.api.v2;

import com.api.wishlist.config.exceptions.BusinessException;
import com.api.wishlist.config.exceptions.ExceptionMessage;
import com.api.wishlist.config.jwt.JwtUtils;
import com.api.wishlist.controller.api.AuthController;
import com.api.wishlist.controller.request.CreateTokenRequest;
import com.api.wishlist.controller.request.GenerateBearerTokenRequest;
import com.api.wishlist.controller.response.CreateUserResponse;
import com.api.wishlist.controller.response.JwtResponse;
import com.api.wishlist.domain.model.ServiceClient;
import com.api.wishlist.repository.AuthRepository;
import com.api.wishlist.service.AuthImpl;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Qualifier("AuthController-V2")
@RestController
@RequestMapping("api/v2")
public class AuthControllerImpl implements AuthController {

	private final AuthenticationManager authenticationManager;
	private final AuthRepository authRepository;
	private final PasswordEncoder encoder;
	private final JwtUtils jwtUtils;

	@Override
	public CreateUserResponse createUser(CreateTokenRequest request) {

		if(StringUtils.isEmpty(request.getName())) {
			throw new BusinessException(ExceptionMessage.ERROR_VALIDATION_MESSAGE);
		}

		var unicToken = UUID.randomUUID().toString().toLowerCase().replace("-", "");
		var user = new ServiceClient(request.getName(), unicToken, encoder.encode(unicToken));

		authRepository.save(user);

		return new CreateUserResponse(unicToken);
	}

	@Override
	public ResponseEntity<JwtResponse> authenticateConsumer(GenerateBearerTokenRequest request) {

		var authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getPassKey(), request.getPassKey()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		var jwt = jwtUtils.generateJwtToken(authentication);

		var userDetails = (AuthImpl) authentication.getPrincipal();
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId()));
	}
}