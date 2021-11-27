package com.newts.newtapp.api.security;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    public static final String USER = "USER";

    @Override
    public String getAuthority() {
        return USER;
    }
}
