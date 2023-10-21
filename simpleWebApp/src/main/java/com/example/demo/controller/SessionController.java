package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes({"name"})
public class SessionController {

    public SessionController() {
    }

    // http://localhost:8080/displaysession
    @GetMapping("displaysession")
    public String display() {
        return "displaySession";
    }

    // http://localhost:8080/clearsession
    @GetMapping("clearsession")
    public String clear(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "displaySession";
    }

    // http://localhost:8080/changename
    @GetMapping("changename")
    public String changeName(Model model) {
        model.addAttribute("name", "John");
        model.addAttribute("name2", "John2");
        return "displaySession";
    }
}
