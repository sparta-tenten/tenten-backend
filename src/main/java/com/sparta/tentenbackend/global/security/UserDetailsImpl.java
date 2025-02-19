package com.sparta.tentenbackend.global.security;

import com.sparta.tentenbackend.domain.user.entity.User;

import lombok.Getter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class UserDetailsImpl implements UserDetails {
	private final User user;

	public UserDetailsImpl(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();  // 권한 정보 없음
	}

	@Override
	public String getPassword() {
		return user.getPassword();  // 유저 비밀번호 반환
	}

	@Override
	public String getUsername() {
		return user.getUserName();  // 유저 이름 반환
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
}
