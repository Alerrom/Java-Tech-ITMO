package ru.itmo.kotiki.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailService {
    UserDetails loadUserByUsername(String username);
}
