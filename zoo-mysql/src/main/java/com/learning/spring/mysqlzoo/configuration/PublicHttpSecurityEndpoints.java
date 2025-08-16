package com.learning.spring.mysqlzoo.configuration;

import java.util.List;

import org.springframework.stereotype.Component;

import com.learning.spring.mysqlsecurity.PublicEndpointCustomizer;

@Component
public class PublicHttpSecurityEndpoints  implements PublicEndpointCustomizer {
	@Override
	public List<String> publicEndpoints() {
		// TODO Auto-generated method stub
		return List.of("/user/**", "/error");
	}
}
