package com.pansauce.controller;

import com.pansauce.constants.enums.UserErrorMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class LoginController {

    @PostMapping("/login")
    public void login(){}

    @GetMapping("/role")
    public String getUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<String> authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return authorities.stream().findFirst().orElseThrow(() ->
                new UsernameNotFoundException(UserErrorMessage.NON_EXISTING_USER.toString()));
    }

}
