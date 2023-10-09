package com.example.shelter.entities;

import com.example.shelter.model.AnimalSpecies;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    public UUID id;

    @NotNull
    public AnimalSpecies species;

    @NotNull
    public String vaccinated;

    public String name;
    public String sex;
    public String size;
    public Integer age;

    @NotNull
    @NotBlank
    public String arrival;

    @NotNull
    @NotBlank
    public String location;

}
