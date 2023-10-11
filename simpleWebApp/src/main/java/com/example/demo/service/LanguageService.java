package com.example.demo.service;

import com.example.demo.model.Language;
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
        return languageRepository.getLanguages();
    }
}
