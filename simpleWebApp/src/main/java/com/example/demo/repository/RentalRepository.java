package com.example.demo.repository;

import com.example.demo.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByRentalDateBetween(LocalDate startDate, LocalDate endDate);
}

