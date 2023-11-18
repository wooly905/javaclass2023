package com.example.demo.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class LanguageV2Dto {

    private Long id;

    @JsonProperty("language-name")
    private String name;

    public LanguageV2Dto(Long id, String name, LocalDateTime lastUpdate) {
        this.id = id;
        this.name = name;
        this.lastUpdate = lastUpdate;
    }

    private LocalDateTime lastUpdate;
}
