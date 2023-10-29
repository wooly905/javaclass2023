package com.example.demo.repository;

import com.example.demo.entity.FilmActor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmActorRepository extends JpaRepository<FilmActor, Long> {
    List<FilmActor> findAll(Sort sort);
}
