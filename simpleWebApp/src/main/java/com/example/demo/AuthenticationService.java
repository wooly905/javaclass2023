package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public Boolean Authenticate (String username, String password) {
        String hashedPassword = SHA512.getSHA512(password);
        return username.equals("admin") && hashedPassword.equals("c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec");
    }
}
