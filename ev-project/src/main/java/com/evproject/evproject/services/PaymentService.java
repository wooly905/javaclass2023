package com.evproject.evproject.services;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public Integer getAmount(Integer chargeWatt)
    {
        // 假設每度電 5 元
        return chargeWatt * 5;
    }
}
