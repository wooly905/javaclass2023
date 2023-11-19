package com.example.demo.repository;

import com.example.demo.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Query(value = "SELECT actor_id, first_name, last_name, last_update FROM actor WHERE CONCAT(first_name, ' ', last_name) = :fullNames", nativeQuery = true)
    List<Actor> findByFullName(@Param("fullNames") String fullName);
}
