package com.example.shelter.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="DOGS")

public class Dog {
    @Id
    private int id;

    private String name;


    public Dog(int id, String name)  {
        this.id = id;
        this.name = name;
    }

    public Dog() {
    }

     public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
