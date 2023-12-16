package com.evproject.evproject.models;

public class CustomerSessionInfo {

    private String customerName;
    private Long customerId;
    private String chargingId;

    // Constructors, getters, and setters

    public CustomerSessionInfo(String customerName, Long customerId, String chargingId) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.chargingId = chargingId;
    }

    // Getters and setters

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getChargingId() {
        return chargingId;
    }

    public void setChargingId(String chargingId) {
        this.chargingId = chargingId;
    }
}
