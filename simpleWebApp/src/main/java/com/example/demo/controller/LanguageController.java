package com.example.demo.controller;

import com.example.demo.model.Language;
import com.example.demo.service.LanguageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LanguageController {
    private LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("displaylanguages")
    public String getLanguages(Model model) {
        List<Language> languages = languageService.getLanguages();
        model.addAttribute("languages", languages);
        return "displaylanguages";
    }
}
