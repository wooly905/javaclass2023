package com.example.demo.repository;

import com.example.demo.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    @Query(value = "select language_id, name, last_update from language where name = ?1", nativeQuery = true)
    Language findByName(String name);

    @Query(value = "select language_id, name, last_update from language", nativeQuery = true)
    List<Language> getLanguages();
}
