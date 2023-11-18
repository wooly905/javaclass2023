package com.example.demo.repository;

import com.example.demo.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    @Query( value = "select language_id, name, last_update from language where name = :name" , nativeQuery = true)
    public List<Language> getLanguageByName(@Param("name") String name);
}
