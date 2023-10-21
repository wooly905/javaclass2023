package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "auth-service")
@Component
public class AuthConfig {
    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    private String way;

}
