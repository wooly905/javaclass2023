package com.example.demo.service;


public class AuthPasswordService implements AuthService  {
    public Boolean Authenticate (String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }
}
