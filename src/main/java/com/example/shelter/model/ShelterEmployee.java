package com.example.shelter.model;

public abstract class ShelterEmployee {
    private String firstName;
    private String lastName;
    private String function;
    private String workPlace;

    public ShelterEmployee(String firstName, String lastName, String function, String workPlace) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.function = function;
        this.workPlace = workPlace;
    }
}
