package com.pansauce.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccessUserDetailsService implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    public AccessUserDetailsService(
            @Qualifier(value = "userRepo")
            UserDetailsRepository userDetailsRepository
    ) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userDetailsRepository.loadUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);
        return user;
    }

}