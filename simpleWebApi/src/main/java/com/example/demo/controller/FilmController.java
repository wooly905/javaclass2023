package com.example.demo.controller;

import com.example.demo.Dto.FilmRequestDto;
import com.example.demo.entity.Actor;
import com.example.demo.entity.Category;
import com.example.demo.entity.Film;
import com.example.demo.entity.Language;
import com.example.demo.repository.ActorRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.FilmRepository;
import com.example.demo.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/{version}")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/films")
    public ResponseEntity<?> getFilms() {
        List<Film> films = filmRepository.findAll();
        return ResponseEntity.ok(films);
    }

    @PostMapping("/films")
    public ResponseEntity<?> addFilm(@RequestBody FilmRequestDto filmDto) {
        // verify film title
        List<Film> films = filmRepository.findByTitle(filmDto.getTitle());

        if (!films.isEmpty()) {
            return ResponseEntity.badRequest().body("Title already exists");
        }

        // verify language
        List<Language> langs = languageRepository.getLanguageByName(filmDto.getLanguageName());

        if (langs.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid language name");
        }

        // verify actors
        List<Actor> actors = new ArrayList();

        for (String fullName : filmDto.getActorFullNames()) {
            List<Actor> actorsByName = actorRepository.findByFullName(fullName);
            if (actorsByName.isEmpty()) {
                return ResponseEntity.badRequest().body("Invalid actor name");
            } else {
                actors.addAll(actorsByName);
            }
        }

        // verify categories
        List<Category> categories = new ArrayList<>();

        for (String cate : filmDto.getCategoryNames()) {
            Optional<Category> categoryOptional = categoryRepository.findByName(cate);

            if (!categoryOptional.isPresent()) {
                return ResponseEntity.badRequest().body("Invalid category name");
            } else {
                categories.add(categoryOptional.get());
            }
        }

        Film film = new Film();
        film.setTitle(filmDto.getTitle());
        film.setDescription(filmDto.getDescription());
        film.setReleaseYear(filmDto.getReleaseYear());
        film.setLanguage(langs.get(0));
        film.setActors(actors);
        film.setCategories(categories);
        filmRepository.save(film);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(film.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
