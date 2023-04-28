package com.api.wishlist.service;

import com.api.wishlist.domain.model.ServiceClient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Objects;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

@Getter
public class AuthImpl implements UserDetails {

	private String id;

	private String name;

	private String clientKey;

	@JsonIgnore
	private String hashKey;

	public AuthImpl(String id, String name, String clientKey, String hashKey) {
		this.id = id;
		this.name = name;
		this.clientKey = clientKey;
		this.hashKey = hashKey;
	}

	@Transactional
	public static AuthImpl build(ServiceClient user) {

		return new AuthImpl(
				user.getId(), 
				user.getName(),
				user.getClientKey(),
				user.getHashKey());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return null;
	}

	@Override
	public String getPassword() {
		return getHashKey();
	}

	@Override
	public String getUsername() {
		return getClientKey();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AuthImpl user = (AuthImpl) o;
		return Objects.equals(id, user.id);
	}
}