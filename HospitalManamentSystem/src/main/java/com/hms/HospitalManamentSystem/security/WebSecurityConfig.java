package com.hms.HospitalManamentSystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.FormLoginBeanDefinitionParser;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
	
	private final JwtAuthFilter jwtAuthFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
	{
		httpSecurity
		          .csrf(csrfConfig->csrfConfig.disable())
		          .sessionManagement(sessionConfig ->
		                          sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		          .authorizeHttpRequests(auth -> auth
		        		                         .requestMatchers("/public/**","/auth/**").permitAll()
		        		                         //.requestMatchers("/admin/**").hasRole("ADMIN")
		        		                         .anyRequest().authenticated()
		        		  )
		          .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		          
		return httpSecurity.build();
	}
}
