package com.example.demo.controller;

import com.example.demo.model.Actor;
import com.example.demo.repository.ActorRepository;
import com.example.demo.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ActorService actorService;

    // http://localhost:8080/displayactors
    @GetMapping("displayactors")
    public String getActors(Model model) {
        List<Actor> actors = actorRepository.getActors();
        model.addAttribute("actors", actors);
        return "displayActors";
    }

    // http://localhost:8080/displaypagedactors
    @GetMapping("displaypagedactors")
    public String getPagedActors(Model model,
                                 @RequestParam("page") Optional<Integer> page,
                                 @RequestParam("size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(20);

        Page<Actor> actorPage = actorService.getActors(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("actorPage", actorPage);

        int totalPages = actorPage.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                                                 .boxed()
                                                 .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "pageActors.html";
    }
}
