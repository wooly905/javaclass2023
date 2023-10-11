package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Controller
@SessionAttributes("name")
public class LoginController {

//    private AuthenticationService authService;
//
//    public LoginController(AuthenticationService authService) {
//        this.authService = authService;
//    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(@RequestParam("name") Optional<String> name, @RequestParam("password") Optional<String> password, Model model) {
        String nameValue = name.orElse("");
        String passwordValue = password.orElse("");

        if (nameValue.equals("admin") && passwordValue.equals("admin")) {
            model.addAttribute("name", nameValue);
            return "welcome";
        }

        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login2(@RequestParam("name") Optional<String> name, @RequestParam("password") Optional<String> password, Model model) {
        String nameValue = name.orElse("");
        String passwordValue = password.orElse("");

        if (nameValue.equals("admin") && passwordValue.equals("admin")) {
            model.addAttribute("name", nameValue);
            return "welcome";
        }

        return "login";
    }

//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    public String login2(@RequestParam("name") Optional<String> name, @RequestParam("password") Optional<String> password, Model model) {
//        String nameValue = name.orElse("");
//        String passwordValue = password.orElse("");
//
//        if (authService.Authenticate(nameValue, passwordValue)) {
//            model.addAttribute("name", nameValue);
//            return "welcome";
//        }
//
//        return "login";
//    }
}
