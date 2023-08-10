package com.example.shelter.model;

public abstract class Animal {
    private int id;
    private String name;
    private String size;
    private int age;
    private int arrival;
    private String whereLocated;
    private boolean quarantine;
    private boolean adopted;

    public Animal(int id, String name, String size, int age, int arrival, String whereLocated, boolean quarantine, boolean adopted) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.age = age;
        this.arrival = arrival;
        this.whereLocated = whereLocated;
        this.quarantine = quarantine;
        this.adopted = adopted;
    }

    public Animal() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public int getAge() {
        return age;
    }

    public int getArrival() {
        return arrival;
    }

    public String getWhereLocated() {
        return whereLocated;
    }

    public boolean isQuarantine() {
        return quarantine;
    }

    public boolean isAdopted() {
        return adopted;
    }
}
