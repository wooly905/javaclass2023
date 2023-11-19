package com.example.demo.repository;

import com.example.demo.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query(value = "SELECT * FROM film WHERE title = :title", nativeQuery = true)
    public List<Film> findByTitle(String title);
}
