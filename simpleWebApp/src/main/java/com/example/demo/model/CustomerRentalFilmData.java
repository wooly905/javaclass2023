package com.example.demo.model;

import java.time.LocalDateTime;

public class CustomerRentalFilmData {

    private int rentalId;
    private LocalDateTime rentalDate;
    private String FilmTitle;

    public CustomerRentalFilmData(int rentalId, LocalDateTime rentalDate, String filmTitle) {
        this.rentalId = rentalId;
        this.rentalDate = rentalDate;
        FilmTitle = filmTitle;
    }

    public int getRentalId() {
        return rentalId;
    }

    public LocalDateTime getRentalDate() {
        return rentalDate;
    }

    public String getFilmTitle() {
        return FilmTitle;
    }
}
