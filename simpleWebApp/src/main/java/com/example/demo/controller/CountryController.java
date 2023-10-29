package com.example.demo.controller;

import com.example.demo.entity.Country;
import com.example.demo.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;

    // http://localhost:8080/country-city-list
    @GetMapping("/country-city-list")
    public String listCountries(Model model) {
        List<Country> countries = countryService.findAll();
        model.addAttribute("countries", countries);
        return "/sakila/country-city-list.html";
    }
}
