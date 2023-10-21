package com.example.demo.model;

public class ActorData {
    private int actorId;
    private String actorName;
    private int rentalId;

    public ActorData(int actorId, String actorName, int rentalId) {
        this.actorId = actorId;
        this.actorName = actorName;
        this.rentalId = rentalId;
    }

    public int getActorId() {
        return actorId;
    }

    public String getActorName() {
        return actorName;
    }

    public int getRentalId() {
        return rentalId;
    }
}
