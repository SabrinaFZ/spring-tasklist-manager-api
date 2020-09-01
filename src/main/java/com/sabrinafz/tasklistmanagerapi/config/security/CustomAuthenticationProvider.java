package com.sabrinafz.tasklistmanagerapi.config.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sabrinafz.tasklistmanagerapi.entity.user.User;
import com.sabrinafz.tasklistmanagerapi.service.user.UserService;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		if (email == null || password == null) {
			throw new BadCredentialsException("Wrong user or password");
		}
		
		User tempUser = userService.getUserByEmail(email);
		
		if (tempUser == null) {
			throw new UsernameNotFoundException("User not found with email " + email);
		}
		
		if (!(bCryptPasswordEncoder.matches(password, tempUser.getPassword().substring(8, tempUser.getPassword().length())))) {
			throw new BadCredentialsException("Wrong password");
		}
		
		return new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
