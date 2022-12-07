package com.devgurupk.keycloakclient.security;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;

public class UserAuthenticationService implements UserDetailsService {


    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO: invoke infoURI and get userDetails and cache it (@Cacheable)
        //either
        //TODO: Get the user related information using token introspect http://localhost:8080/realms/master/protocol/openid-connect/token/introspect
        return new AuthUserInfo(List.of("ROLE_ADMIM"),1l,"Admin","admin",null,true);
    }
}
