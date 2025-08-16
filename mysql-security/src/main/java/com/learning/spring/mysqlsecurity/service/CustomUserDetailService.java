package com.learning.spring.mysqlsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learning.spring.mysqlsecurity.entity.User;
import com.learning.spring.mysqlsecurity.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService 
{
	@Autowired
	private UserRepository userRepository;


	/** 
	 * Only difference with default constructor is that @PostContruct is called after all dependency injection is resolved.
	 * that's why userReposiotry will not be null
	 */
//    @PostConstruct
//    public void init() {
//        // Safe to use 'dependency' here
//    	userRepository.initialize();
//    }
	
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		return user;	
	}
}
