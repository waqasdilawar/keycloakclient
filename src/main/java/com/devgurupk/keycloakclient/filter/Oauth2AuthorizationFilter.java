package com.devgurupk.keycloakclient.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class Oauth2AuthorizationFilter extends GenericFilterBean
{
    private final UserDetailsService userDetailsService;

    public Oauth2AuthorizationFilter(UserDetailsService userDetailsService)
    {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {

        var context = SecurityContextHolder.getContext();
        if (context.getAuthentication() != null && (context.getAuthentication().getPrincipal() instanceof Jwt))
        {

            var user = userDetailsService.loadUserByUsername(((Jwt) context.getAuthentication().getPrincipal()).getClaimAsString("preferred_username"));
            var authentication = new UsernamePasswordAuthenticationToken(user, null, context.getAuthentication().getAuthorities());
            context.setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}
