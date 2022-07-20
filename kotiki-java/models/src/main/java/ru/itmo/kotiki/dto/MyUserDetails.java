package ru.itmo.kotiki.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itmo.kotiki.ampq.RabbitUserInfo;
import ru.itmo.kotiki.entity.Role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class MyUserDetails implements UserDetails {

    private RabbitUserInfo user;

    public MyUserDetails(RabbitUserInfo user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = Set.of();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole().name()));
        }
        return authorities;
    }
    public RabbitUserInfo getUser(){
        return user;
    }

    @Override
    public String getPassword() {
        return user.password();
    }

    @Override
    public String getUsername() {
        return user.name();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.flag();
    }
}