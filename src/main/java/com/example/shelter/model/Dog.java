package com.example.shelter.model;

import com.sun.jdi.PrimitiveValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="DOGS")

public class Dog {
    @Id
    private int id;

    private String name;


    public Dog(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Dog() {
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
