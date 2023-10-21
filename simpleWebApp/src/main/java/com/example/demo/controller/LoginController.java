package com.example.demo.controller;

import com.example.demo.service.AuthService;
import com.example.demo.service.AuthServiceSelector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@SessionAttributes({"name"} )
public class LoginController {

    private AuthService authService;

    public LoginController(AuthServiceSelector authSelector) {
        this.authService = authSelector.GetAuthService();
    }

    // http://localhost:8080/login?name=admin&password=admin
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

    // http://localhost:8080/login
//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    public String login2(@RequestParam("name") Optional<String> name, @RequestParam("password") Optional<String> password, Model model) {
//        String nameValue = name.orElse("");
//        String passwordValue = password.orElse("");
//
//        if (nameValue.equals("admin") && passwordValue.equals("admin")) {
//            model.addAttribute("name", nameValue);
//            return "welcome";
//        }
//
//        return "login";
//    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login3(@RequestParam("name") Optional<String> name, @RequestParam("password") Optional<String> password, Model model) {
        String nameValue = name.orElse("");
        String passwordValue = password.orElse("");

        if (authService.Authenticate(nameValue, passwordValue)) {
            model.addAttribute("name", nameValue);
            return "welcome";
        }

        return "login";
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("viewpage")
    public String viewpage(Model model) {
        if (!model.containsAttribute("name")){
            return "login";
        }

        logger.info("name2 = " + model.getAttribute("name2"));
        logger.info("name = " + model.getAttribute("name"));

        return "viewPage";
    }
}
