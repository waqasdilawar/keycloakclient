package com.devgurupk.keycloakclient.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;


public class AuthUserInfo implements UserDetails
{
    private final List<String> userRoles;
    private final Long webId;
    private final String name;
    private final String userName;
    private final String password;
    private final Boolean admin;

    public AuthUserInfo(List<String> userRoles, Long webId, String name, String userName, String password, Boolean admin) {
        this.userRoles = userRoles;
        this.webId = webId;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.admin = admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        var roles = StringUtils.collectionToCommaDelimitedString(userRoles);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }

    @Override
    public String getUsername()
    {
        return this.userName;
    }

    public boolean isAdmin()
    {
        return admin;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    public List<String> getUserRoles()
    {
        return userRoles;
    }

    public Long getWebId()
    {
        return webId;
    }


    public String getName()
    {
        return name;
    }
}
