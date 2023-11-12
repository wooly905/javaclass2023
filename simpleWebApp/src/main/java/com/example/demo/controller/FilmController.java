package com.example.demo.controller;

import com.example.demo.entity.Actor;
import com.example.demo.entity.Film;
import com.example.demo.repository.ActorRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.FilmRepository;
import com.example.demo.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

@Controller
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ActorRepository actorRepository;

    @GetMapping("/film/{filmId}")
    public String viewFilm(@PathVariable Long filmId, Model model) {
        Optional<Film> filmOptional = filmRepository.findById(filmId);
        if (filmOptional.isPresent()) {
            model.addAttribute("film", filmOptional.get());
            return "/sakila/film-detail";
        } else {
            return "errorPage";  // return to an error page or other handling
        }
    }

    @GetMapping("/film-list")
    public String getFilms(Model model) {
        List<Film> films = filmRepository.findAll();
        model.addAttribute("films", films);
        return "/sakila/film-list.html";
    }

    @GetMapping("/addFilm")
    public String showAddFilmForm(Model model) {
        model.addAttribute("film", new Film());
        model.addAttribute("languages", languageRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("actors", actorRepository.findAll());
        return "/sakila/add-film.html";
    }

    @PostMapping("/addFilm")
    public String addFilm(@RequestParam(value = "specialFeatures", required = false) String[] specialFeatures,
                          @ModelAttribute("film") Film film) {
        if (specialFeatures != null) {
            StringJoiner joiner = new StringJoiner(",");

            for (String feature : specialFeatures) {
                joiner.add(feature);
            }
            film.setSpecialFeatures(joiner.toString());
        }

        filmRepository.save(film);
        return "redirect:/film-list";
    }

    @GetMapping("/removeFilm/{filmId}")
    public String removeFilm(@PathVariable Long filmId) {
        filmRepository.deleteById(filmId);
        return "redirect:/film-list";
    }
}