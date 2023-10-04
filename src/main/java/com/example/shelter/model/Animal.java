package com.example.shelter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public abstract class Animal {

    @Id
    public int id;
    public String name;
    @Column()
    public String sex;
    @Column()
    public String size;
    @Column()
    public Integer age;
    @Column()
    public String arrival;
    @Column()
    public String location;
}
