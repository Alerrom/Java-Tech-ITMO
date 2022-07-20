package ru.itmo.kotiki.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailService {
    UserDetails loadUserByUsername(String username) throws JsonProcessingException;
}
