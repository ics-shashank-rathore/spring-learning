package com.learning.spring.mysqlsecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learning.spring.mysqlsecurity.audit.AuditAwareImpl;

@Configuration
public class AuditAwareConfiguration {

	/**
	 * Control over Creation
	 * Method level
	 * Conditional
	 * 
	 * @return
	 */
    @Bean
    AuditAwareImpl auditAware()
	{
		return new AuditAwareImpl();
	}
}
