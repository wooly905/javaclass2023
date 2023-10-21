package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SalesDataYearMonth {
    private int rentalId ;
    private String customerName;
    private BigDecimal amount;
    private LocalDateTime paymentDate;

    public SalesDataYearMonth(int rentalId, String customerName, BigDecimal amount, LocalDateTime paymentDate) {
        this.rentalId = rentalId;
        this.customerName = customerName;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }


    public int getRentalId() {
        return rentalId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
}
