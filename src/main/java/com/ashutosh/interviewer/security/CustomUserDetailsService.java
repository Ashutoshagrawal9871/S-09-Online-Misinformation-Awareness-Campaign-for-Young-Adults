package com.ashutosh.interviewer.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin@interviewer.com".equals(username) || "user@interviewer.com".equals(username)) {
            return new User(username, "{noop}password123", Collections.emptyList());
        }
        throw new UsernameNotFoundException("User not found with email: " + username);
    }
}