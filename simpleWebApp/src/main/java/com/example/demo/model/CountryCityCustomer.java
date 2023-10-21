package com.example.demo.model;

public class CountryCityCustomer {
    public CountryCityCustomer(int countryId, String countryName, int cityId, String cityName, int customerId, String customerEmail) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.cityId = cityId;
        this.cityName = cityName;
        this.customerId = customerId;
        this.customerEmail = customerEmail;
    }

    private int countryId;
    private String countryName;
    private int cityId;
    private String cityName;
    private int customerId;

    public int getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    private String customerEmail;

}
