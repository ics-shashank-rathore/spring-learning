package com.learning.spring.mysqlzoo.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.learning.spring.mysqlsecurity.entity.AuthToken;
import com.learning.spring.mysqlsecurity.entity.User;
import com.learning.spring.mysqlsecurity.repository.AuthTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthTokenService 
{
	/**
	 * No need of autowired, as @RequiredArgsConstructor
	 */
    private final AuthTokenRepository authTokenRepository;
    
	public AuthToken save(User user)
	{
		String rawToken = user.getUsername() + System.currentTimeMillis();

		AuthToken authToken = new AuthToken();
		authToken.setToken(rawToken);	
		authToken.setUser(user);
		authToken.setCreatedOn(LocalDateTime.now());
		authToken.setExpiresAt(LocalDateTime.now().plusHours(2));
		
		return authTokenRepository.save(authToken);
	}
}
