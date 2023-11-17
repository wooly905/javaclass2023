package com.example.demo.controller;

import com.example.demo.entity.Language;
import com.example.demo.repository.LanguageRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name ="Language service", description = "API to manage languages in Sakila DVD Rental database")
@RestController
@RequestMapping("/api")
public class LanguageController {

    @Autowired
    private LanguageRepository languageRepository;

    // get all languages here (GET /languages)
    @GetMapping("/languages")
    public ResponseEntity<List<Language>> getAllLanguages() {
        List<Language> langs = languageRepository.findAll();
        return ResponseEntity.ok(langs);
    }

    // get language by id here
    @GetMapping("/languages/{id}")
    public ResponseEntity<Language> getLanguageById(@PathVariable(value = "id") Long id) {
        Optional<Language> lang = languageRepository.findById(id);

        if (lang.isPresent()) {
            return ResponseEntity.ok(lang.get());
        }

        return ResponseEntity.notFound().build();
    }

    // create language here
    @PostMapping("/languages")
    public ResponseEntity<Language> createLanguage(@RequestBody Language lang) {
        Language newLang = languageRepository.save(lang);
        return ResponseEntity.ok(newLang);
    }

    // update language here
    @PutMapping("/languages/{id}")
    public ResponseEntity<Language> updateLanguage(@PathVariable(value = "id") Long id, @RequestBody Language lang) {
        Optional<Language> existingLang = languageRepository.findById(id);

        if (existingLang.isPresent()) {
            Language newLang = existingLang.get();
            newLang.setName(lang.getName());
            languageRepository.save(newLang);
            return ResponseEntity.ok(newLang);
        }

        return ResponseEntity.notFound().build();
    }

    // delete language here
    @DeleteMapping("/languages/{id}")
    public ResponseEntity<Language> deleteLanguage(@PathVariable(value = "id") Long id) {
        Optional<Language> existingLang = languageRepository.findById(id);

        if (existingLang.isPresent()) {
            languageRepository.delete(existingLang.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
