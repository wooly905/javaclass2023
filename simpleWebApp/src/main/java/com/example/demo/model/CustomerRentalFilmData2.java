package com.example.demo.model;

import java.time.LocalDateTime;

public class CustomerRentalFilmData2 {
    private int rentalId;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private String FilmTitle;

    public CustomerRentalFilmData2(int rentalId, LocalDateTime rentalDate, LocalDateTime returnDate, String filmTitle) {
        this.rentalId = rentalId;
        this.rentalDate = rentalDate;
        FilmTitle = filmTitle;
        this.returnDate = returnDate;
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

    public LocalDateTime getReturnDate() {
        return returnDate;
    }
}
