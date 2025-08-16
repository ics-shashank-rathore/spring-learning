package com.learning.spring.mysqlzoo.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.spring.common.response.ApiResponse;
import com.learning.spring.common.response.ApiResponseMapper;
import com.learning.spring.mysqlsecurity.entity.AuthToken;
import com.learning.spring.mysqlsecurity.entity.User;
import com.learning.spring.mysqlzoo.request.dto.UserLoginDTO;
import com.learning.spring.mysqlzoo.services.AuthTokenService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController 
{
	private final AuthenticationManager authenticationManager;
	private final AuthTokenService authTokenService;

	@PostMapping("login")
	public ResponseEntity<ApiResponse<Map<String, String>>> login(@RequestBody UserLoginDTO userLoginDTO) 
	{
		try
		{
			// Delegates to customer userdetails service
			Authentication authentication =  authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword())
					);

			// Generate Token		
			AuthToken authToken = authTokenService.save((User)authentication.getPrincipal());

			return ResponseEntity.ok(ApiResponseMapper.toApiResponse(Map.of("message", authToken.getToken())));
		}
		catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResponseMapper.toApiResponse(Map.of("message", "Invalid username or password")));
		}

	}

}
