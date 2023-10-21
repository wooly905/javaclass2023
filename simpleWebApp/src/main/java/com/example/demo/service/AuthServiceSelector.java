package com.example.demo.service;

import com.example.demo.config.AuthConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceSelector {

    @Autowired
    private AuthConfig authConfig;

    public AuthService GetAuthService() {

        String way = authConfig.getWay();

        if (way.equals("password")) {
            return new AuthPasswordService();
        } else if (way.equals("hash")) {
            return new AuthenticationService();
        } else {
            return null;
        }
    }
}
