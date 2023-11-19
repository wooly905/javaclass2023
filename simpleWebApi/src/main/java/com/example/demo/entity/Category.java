package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss", timezone = "GMT+8")
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
