package com.example.demo.controller;

import com.example.demo.entity.FilmActor;
import com.example.demo.repository.FilmActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FilmActorController {

    @Autowired
    private FilmActorRepository repository;

    // http://localhost:8080/film-actor
    @GetMapping("/film-actor")
    public String list(Model model) {
        Sort sort = Sort.by(Sort.Direction.ASC, "actor", "film");
        List<FilmActor> filmActors = repository.findAll(sort);
        model.addAttribute("filmActors", filmActors);
        return "film-actor-list";
    }

}
