package com.example.demo.service;

import com.example.demo.entity.Language;
import com.example.demo.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public Language findByName(String name) {
        return languageRepository.findByName(name);
    }

    public List<Language> getLanguages() {
        return languageRepository.findAll();
    }

    // add a new language to the database
    public void save(Language language) {
        languageRepository.save(language);
    }

    public void delete(Long id) {
        languageRepository.deleteById(id);
    }

    public Language get(Long id) {
        return languageRepository.findById(id).get();
    }
}
