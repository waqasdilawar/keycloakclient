package com.devgurupk.keycloakclient;

import com.devgurupk.keycloakclient.security.UserAuthenticationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootApplication
public class KeycloakClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeycloakClientApplication.class, args);
	}

	@Bean
	public UserDetailsService userDetailsService()
	{
		return new UserAuthenticationService();
	}
}
