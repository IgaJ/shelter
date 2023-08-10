package com.example.shelter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="CATS")
public class Cat {
    @Id
    private int id;
    private String name;

    public Cat(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Cat() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
