package com.example.shelter.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Table(name="DOGS")
@Component
public class Dog extends Animal{


    public Dog(int id, String name, String sex, String size, Integer age, String arrival, String location) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.size = size;
        this.age = age;
        this.arrival = arrival;
        this.location = location;
    }

    public Dog() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getSize() {
        return size;
    }

    public Integer getAge() {
        return age;
    }

    public String getArrival() {
        return arrival;
    }

    public String getLocation() {
        return location;
    }
}
