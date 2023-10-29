package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "country")
    private String country;

    @Column(name = "last_update")
    private LocalDate lastUpdate;

    @OneToMany(mappedBy = "country")
    //@JoinColumn(name = "country_id")
    private List<City> cities;
}
