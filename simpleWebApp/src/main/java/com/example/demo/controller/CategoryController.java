package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.entity.Film;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("category-list")
    public String getCategories(Model model) {
        List<Category> cate = categoryRepository.findAll();

        for (Category category : cate) {
            category.getFilms().sort(Comparator.comparing(Film::getTitle).reversed());
        }

        model.addAttribute("categories", cate);
        return "/sakila/category-list.html";
    }
}
