package com.learning.spring.mysqlsecurity.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.learning.spring.mysqlsecurity.PublicEndpointCustomizer;
import com.learning.spring.mysqlsecurity.filter.PrivateSecurityFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@EnableJpaAuditing(auditorAwareRef = "auditAware")
@EnableAspectJAutoProxy
public class SpringSecurityConfiguration {

	// Multiple app will implement, that's why list
	private final List<PublicEndpointCustomizer> customizers;

	public SpringSecurityConfiguration(List<PublicEndpointCustomizer> customizers) {
		this.customizers = customizers;
	}

    /**
     * By default Bean will work in  dependency order.
     * eg. privateSecurityFilter depends on publicPaths
     * therefore publicPaths resolve first
     * @return
     */
    @Bean
    List<String> publicPaths() {
		return customizers.stream()
				.flatMap(c -> c.publicEndpoints().stream())
				.distinct()
				.toList();
	}

    @Bean
    PrivateSecurityFilter privateSecurityFilter()
	{
		return new PrivateSecurityFilter(publicPaths());
	}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, PrivateSecurityFilter privateSecurityFilter) throws Exception {
        String[] publicRoutesArray = publicPaths().toArray(new String[0]);

        http
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authz -> authz
                .requestMatchers(publicRoutesArray).permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(privateSecurityFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    AuthenticationManager authManager(AuthenticationConfiguration authenticationConfiguration)
	{
		try {
			return authenticationConfiguration.getAuthenticationManager();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}


    @Bean
    PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
