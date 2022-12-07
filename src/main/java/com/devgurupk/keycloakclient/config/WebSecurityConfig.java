package com.devgurupk.keycloakclient.config;

import com.devgurupk.keycloakclient.filter.Oauth2AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
    @Autowired
    private UserDetailsService userAuthenticationService;
    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/greeting").permitAll()
                        .anyRequest().authenticated())
//                .oauth2ResourceServer().opaqueToken().introspectionUri("http://localhost:8080/realms/master/protocol/openid-connect/token/introspect")
                //for JWKs URI
                .oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter);
        http.addFilterAfter(new Oauth2AuthorizationFilter(userAuthenticationService), SwitchUserFilter.class);
        http.csrf().disable();
        return http.build();
    }

}
