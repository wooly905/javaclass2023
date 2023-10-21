package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    Boolean Authenticate (String username, String password);
}
