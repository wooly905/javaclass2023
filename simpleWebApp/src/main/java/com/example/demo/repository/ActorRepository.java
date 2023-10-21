package com.example.demo.repository;

import com.example.demo.model.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Query(value = "select actor_id, first_name, last_name, last_update from actor", nativeQuery = true)
    List<Actor> getActors();

    @Query(value = "select actor_id, first_name, last_name, last_update from actor", nativeQuery = true)
    List<Actor> getActors(Pageable pageable);
}
