package com.learning.spring.mysqlsecurity.filter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.learning.spring.mysqlsecurity.entity.AuthToken;
import com.learning.spring.mysqlsecurity.entity.User;
import com.learning.spring.mysqlsecurity.repository.AuthTokenRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Bean is created from configuration
 */
public class PrivateSecurityFilter extends OncePerRequestFilter  
{
	@Autowired
	AuthTokenRepository authTokenRepository;

	private final List<String> publicPaths;

	private final AntPathMatcher pathMatcher = new AntPathMatcher();


	public PrivateSecurityFilter(List<String> publicPaths) {
		this.publicPaths = publicPaths;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		String path = request.getRequestURI();
		// Filter not public path
		for (String publicPath : publicPaths) {
			if (pathMatcher.match(publicPath, path)) {
				filterChain.doFilter(request, response);
				return;
			}
		}

		// TODO Auto-generated method stub
		String authHeader = request.getHeader("Authorization");
		Boolean isAuthorize = true;

		if(authHeader == null)
			isAuthorize = false;
		else if(!authHeader.contains("Bearer"))
			isAuthorize = false;

		if(!isAuthorize)
		{
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Missing or invalid token");
			return;	
		}

		String token = authHeader.substring(7);
		Optional<AuthToken> authOptional = authTokenRepository.findByToken(token);
		if(authOptional.isEmpty()
				|| authOptional.get().getExpiresAt().isBefore(LocalDateTime.now()))
		{
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Missing or invalid token");
			return;
		}

		AuthToken authToken = authOptional.get();
		User user = authToken.getUser();

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		filterChain.doFilter(request, response);	
	}

}
