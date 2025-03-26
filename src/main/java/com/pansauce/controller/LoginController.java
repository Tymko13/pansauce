package com.pansauce.controller;

import com.pansauce.security.AccessUserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    AccessUserDetailsService accessUserDetailsService;

    @PostMapping("/login")
    public void login(){}
}
