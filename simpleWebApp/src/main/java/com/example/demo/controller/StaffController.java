package com.example.demo.controller;

import com.example.demo.entity.Staff;
import com.example.demo.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class StaffController {

    @Autowired
    private StaffRepository repository;

    // http://localhost:8080/staff-list
    @GetMapping("/staff-list")
    public String showStaffs(Model model) {
        List<Staff> staffs = repository.findAll();
        model.addAttribute("staffs", staffs);
        return "/sakila/staff-list.html";
    }

    @GetMapping("/staffdetail/{id}")
    public String showStaffDetail(@PathVariable("id") Integer id, Model model) {
        Optional<Staff> op = repository.findById(id);

        if (op.isPresent()) {
            Staff staff = op.get();
            model.addAttribute("staff", staff);
            return "/sakila/staff-detail.html";
        }

        return "redirect:/staff-list";
    }
}
