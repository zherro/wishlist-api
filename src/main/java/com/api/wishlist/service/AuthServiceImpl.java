package com.api.wishlist.service;

import com.api.wishlist.config.exceptions.BlockAccessException;
import com.api.wishlist.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements UserDetailsService {

	private final AuthRepository authRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String clientKey) throws UsernameNotFoundException {
		var user = authRepository.findByClientKey(clientKey)
				.orElseThrow(BlockAccessException::new);
		return AuthImpl.build(user);
	}

}