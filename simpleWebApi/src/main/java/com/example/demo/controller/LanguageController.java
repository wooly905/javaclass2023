package com.example.demo.controller;

import com.example.demo.Dto.LanguageV2Dto;
import com.example.demo.entity.Language;
import com.example.demo.repository.LanguageRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "Language service", description = "API to manage languages in Sakila DVD Rental database")
@RestController
@RequestMapping("/api/{version}")
public class LanguageController {

    @Autowired
    private LanguageRepository repository;

    // get all languages here (GET /languages)
    @GetMapping("/languages")
    public ResponseEntity<?> getAllLanguages(@PathVariable(value = "version") String version) {
        if (version.equals("v1")) {
            List<Language> langs = repository.findAll();
            return ResponseEntity.ok(langs);
        }

        if (version.equals("v2")) {
            List<Language> langs = repository.findAll();
            ArrayList<LanguageV2Dto> langsV2 = new ArrayList<>();

            for (Language lang : langs) {
                LanguageV2Dto v2 = new LanguageV2Dto(lang.getId(), lang.getName(), lang.getLastUpdate());
                langsV2.add(v2);
            }

            return ResponseEntity.ok(langsV2);
        }

        return ResponseEntity.badRequest().build();
    }

    // get language by id here
    @GetMapping("/languages/{id}")
    public ResponseEntity<?> getLanguageById(@PathVariable(value = "version") String version, @PathVariable(value = "id") Long id) {
        Optional<Language> langOptional = repository.findById(id);

        if (langOptional.isPresent()) {
            if (version.equals("v1")) {
                Language lang = langOptional.get();
                return ResponseEntity.ok(lang);
            }

            if (version.equals("v2")) {
                Language lang = langOptional.get();
                LanguageV2Dto l2 = new LanguageV2Dto(lang.getId(), lang.getName(), lang.getLastUpdate());
                return ResponseEntity.ok(l2);
            }

            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.notFound().build();
    }

    // create language here
    @PostMapping("/languages")
    public ResponseEntity<?> createLanguage(@PathVariable(value = "version") String version, @RequestBody Language lang) {

        try {
            String inputName = lang.getName();
            List<Language> langs = repository.getLanguageByName(inputName);

            if (langs.isEmpty()) {
                repository.save(lang);
                URI uri = new URI("http://localhost:8080/languages/");
                return ResponseEntity.created(uri).build();
            }

            return ResponseEntity.badRequest().build();

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // update language here
    @PutMapping("/languages/{id}")
    public ResponseEntity<?> updateLanguage(@PathVariable(value = "version") String version, @PathVariable(value = "id") Long id, @RequestBody Language lang) {
        Optional<Language> existingLang = repository.findById(id);

        if (existingLang.isPresent()) {
            Language newLang = existingLang.get();
            newLang.setName(lang.getName());
            repository.save(newLang);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    // delete language here
    @DeleteMapping("/languages/{id}")
    public ResponseEntity<?> deleteLanguage(@PathVariable(value = "version") String version, @PathVariable(value = "id") Long id) {
        Optional<Language> existingLang = repository.findById(id);

        if (existingLang.isPresent()) {
            repository.delete(existingLang.get());
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();

//        return languageRepository.findById(id).map(language -> {
//            languageRepository.delete(language);
//            return ResponseEntity.ok().build();
//        }).orElse(ResponseEntity.notFound().build());
    }
}
