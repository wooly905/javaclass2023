package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class DemoController {

    @RequestMapping("/courses")
    public List<String> getCourses() {
        return Arrays.asList("Java", "Spring", "SQL", "Angular", "React", "Python");
    }
}
