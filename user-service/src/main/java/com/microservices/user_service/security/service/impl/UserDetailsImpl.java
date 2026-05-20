package com.microservices.user_service.security.service.impl;

import com.microservices.user_service.model.User;
import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;
    private String email;
    Collection<? extends GrantedAuthority> authorities;

    UserDetailsImpl(
            String username,
            String password,
            String email,
            Collection<? extends GrantedAuthority> authorities
    ) {
       this.username = username;
       this.password = password;
       this.email = email;
       this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public static UserDetails build(User user) {
        Collection<? extends GrantedAuthority> authorities1 = user.getRoles()
                .stream()
                .map(item -> new SimpleGrantedAuthority(item.toString()))
                .toList();

        return new UserDetailsImpl(
                user.getPassword(),
                user.getPassword(),
                user.getEmail(),
                authorities1
        );

    }
}
