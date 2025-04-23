package com.pansauce.model.basic;

public class LoginResponse {

    private String authdata;
    private String role;

    public LoginResponse(String role) {
        this.role = role;
    }

    public String getAuthdata() {
        return authdata;
    }

    public void setAuthdata(String authdata) {
        this.authdata = authdata;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}