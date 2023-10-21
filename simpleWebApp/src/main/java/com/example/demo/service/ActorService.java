package com.example.demo.service;

import com.example.demo.model.Actor;
import com.example.demo.repository.ActorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ActorService {
    private List<Actor> actors;

    public ActorService(ActorRepository actorRepository) {
        this.actors = actorRepository.getActors();
    }

    public Page<Actor> getActors(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Actor> list;

        if (actors.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, actors.size());
            list = actors.subList(startItem, toIndex);
        }

        Page<Actor> actorPage = new PageImpl<Actor>(list, PageRequest.of(currentPage, pageSize), actors.size());

        return actorPage;
    }
}
