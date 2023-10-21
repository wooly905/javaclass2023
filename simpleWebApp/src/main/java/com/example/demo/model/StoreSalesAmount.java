package com.example.demo.model;

import java.math.BigDecimal;

public class StoreSalesAmount {
    public StoreSalesAmount(int storeId, BigDecimal amount) {
        this.storeId = storeId;
        this.amount = amount;
    }

    public int getStoreId() {
        return storeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    private int storeId;
    private BigDecimal amount ;

}
