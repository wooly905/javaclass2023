package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "film")
@Data
@EqualsAndHashCode(exclude = "playedActors")
@ToString(exclude = "playedActors")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long filmId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Integer releaseYear;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @Column(name = "rental_duration")
    private Integer rentalDuration;

    @Column(name = "rental_rate")
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Integer length;

    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;

    @Column(name = "rating")
    private String rating;

    @Column(name = "special_features")
    private String specialFeatures;

    @ManyToMany
    @JoinTable(
            name = "film_actor",  // name of the many-to-many table
            joinColumns = @JoinColumn(name = "film_id"), // name of the column in the many-to-many table that points to this table
            inverseJoinColumns = @JoinColumn(name = "actor_id") // name of the column in the many-to-many table that points to the other table
    )
    private Set<Actor> playedActors;
}