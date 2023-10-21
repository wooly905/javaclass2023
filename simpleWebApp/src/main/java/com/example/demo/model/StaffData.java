package com.example.demo.model;

public class StaffData {
    private int staffId;
    private String name;

    public StaffData(int staffId, String name) {
        this.staffId = staffId;
        this.name = name;
    }

    public int getStaffId() {
        return staffId;
    }

    public String getName() {
        return name;
    }
}
