package com.example.demo.controller;

import com.example.demo.entity.Language;
import com.example.demo.service.LanguageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LanguageController {
    private LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    // http://localhost:8080/language-list
    @GetMapping("/language-list")
    public String getLanguages(Model model) {
        List<Language> languages = languageService.getLanguages();
        model.addAttribute("languages", languages);
        return "/sakila/language-list.html";
    }

    @GetMapping("addlanguage")
    public String showAddLanguageForm(Model model) {
        model.addAttribute("language", new Language());
        return "/sakila/addLanguage.html";
    }

    @PostMapping("savelanguage")
    public String addLanguage(@ModelAttribute Language language) {
        languageService.save(language);
        return "redirect:/language-list";
    }

    @GetMapping("/editlanguage/{id}")
    public String editLanguage(@PathVariable("id") Long id, Model model) {
        Language b = languageService.get(id);
        model.addAttribute("language", b);
        return "/sakila/editlanguage.html";
    }

    @GetMapping("deletelanguage/{id}")
    public String deleteLanguage(@PathVariable("id") Long id, Model model) {
        languageService.delete(id);
        return "redirect:/language-list";
    }
}
