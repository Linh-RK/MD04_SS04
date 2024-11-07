package com.ra.md04_ss04_security.security;

import com.ra.md04_ss04_security.model.entity.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserPrinciple implements UserDetails {
    private  User user;

    private Collection<? extends GrantedAuthority> Authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus();
    }
}
