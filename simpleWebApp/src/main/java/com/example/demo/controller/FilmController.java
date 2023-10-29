package com.example.demo.controller;

import com.example.demo.entity.Film;
import com.example.demo.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/film/{filmId}")
    public String viewFilm(@PathVariable Long filmId, Model model) {
        Optional<Film> filmOptional = filmRepository.findById(filmId);
        if (filmOptional.isPresent()) {
            model.addAttribute("film", filmOptional.get());
            return "film-detail";
        } else {
            return "errorPage";  // return to an error page or other handling
        }
    }
}