package com.jd.basicauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jd.basicauth.service.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private RequestLoggingFilter requestLoggingFilter;
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf(AbstractHttpConfigurer::disable).httpBasic(Customizer.withDefaults());
	    http.authorizeHttpRequests(auth -> 
	        auth.requestMatchers("/public/**").permitAll()
	            .anyRequest().authenticated()
	    );
	    http.addFilterBefore(requestLoggingFilter, UsernamePasswordAuthenticationFilter.class);
	    return http.build();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}


}
