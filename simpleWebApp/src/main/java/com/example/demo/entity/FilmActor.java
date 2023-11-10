//package com.example.demo.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Table(name = "film_actor")
//@Data
//public class FilmActor {
//    @EmbeddedId
//    private FilmActorKey id;
//
//    @ManyToOne
//    @MapsId("actorId")
//    @JoinColumn(name = "actor_id")
//    private Actor actor;
//
//    @ManyToOne
//    @MapsId("filmId")
//    @JoinColumn(name = "film_id")
//    private Film film;
//}