package com.example.demo.Controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home()
    {
        String name = SecurityContextHolder.getContext().getAuthentication().getName() ;
        String principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        // get roles
        List<String> roles =  SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String roleValue = String.join(",", roles);

        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Hello World</h1> ");
        sb.append("<h3>name = " + name + "</h3>");
        sb.append("<h3>role = " + roleValue + "</h3>");

        sb.append("<a href='/report1'>report1</a><br>");
        sb.append("<a href='/report2'>report2</a><br>");

        return sb.toString();
    }

    @GetMapping("/report1")
    @Secured("ROLE_ROLE1")
    @ResponseBody
    public String report1()
    {
        return "<h1>Report 1</h1>";
    }

    @GetMapping("/report2")
    @Secured("ROLE_ROLE2")
    @ResponseBody
    public String report2()
    {
        return "<h1>Report 2</h1>";
    }
}
