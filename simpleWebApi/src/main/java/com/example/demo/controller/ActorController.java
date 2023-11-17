package com.example.demo.controller;

import com.example.demo.entity.Actor;
import com.example.demo.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    @GetMapping("/actors")
    public ResponseEntity<List<Actor>> getActors() {
        List<Actor> actors = actorRepository.findAll();
        return ResponseEntity.ok(actors);
    }

    // return a sepcific actor based on actor id
    @GetMapping("/actors/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable(value = "id") Long actorId) {
        Optional<Actor> actorOptional = actorRepository.findById(actorId);

        if (actorOptional.isPresent()) {
            return ResponseEntity.ok(actorOptional.get());
        }

        return ResponseEntity.notFound().build();
    }
}
