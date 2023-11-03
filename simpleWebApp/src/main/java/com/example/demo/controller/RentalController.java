package com.example.demo.controller;

import com.example.demo.entity.Rental;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class RentalController {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/rental-list")
    public String getRentalsBetweenDates(@RequestParam String startDate, @RequestParam String endDate, Model model) {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        List<Rental> rentals = rentalRepository.findByRentalDateBetween(start, end);
        model.addAttribute("rentals", rentals);

        // Assuming Payment entity and PaymentRepository are also created
        Double totalPayment = paymentRepository.findTotalPaymentBetweenDates(start, end);
        model.addAttribute("totalPayment", totalPayment);

        return "/sakila/rentals";
    }
}
