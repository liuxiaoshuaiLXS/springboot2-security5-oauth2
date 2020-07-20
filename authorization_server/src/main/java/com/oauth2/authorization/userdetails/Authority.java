package com.oauth2.authorization.userdetails;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * 角色,映射表authority
 */
public class Authority implements GrantedAuthority, Serializable {
    private Long id;

    private String authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
