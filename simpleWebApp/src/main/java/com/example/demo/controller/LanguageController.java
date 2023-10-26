package com.example.demo.controller;

import com.example.demo.model.Language;
import com.example.demo.repository.LanguageRepository;
import com.example.demo.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
        return "displaylanguages.html";
    }

    @GetMapping("addlanguage")
    public String showAddLanguageForm(Model model) {
        model.addAttribute("language", new Language());
        return "addLanguage";
    }

    @PostMapping("savelanguage")
    public String addLanguage(@ModelAttribute Language language) {
        languageService.save(language);
        return "redirect:/displaylanguages";
    }

    @GetMapping("/editlanguage/{id}")
    public String editLanguage(@PathVariable("id") Long id, Model model) {
        Language b = languageService.get(id);
        model.addAttribute("language", b);
        return "editlanguage";
    }

    @GetMapping("deletelanguage/{id}")
    public String deleteLanguage(@PathVariable("id") Long id, Model model) {
        languageService.delete(id);
        return "redirect:/displaylanguages";
    }
}
