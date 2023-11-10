package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long languageId;

    @Column(name = "name")
    private String name;

    @Column(name = "last_update")
    private LocalDate lastUpdate;

}