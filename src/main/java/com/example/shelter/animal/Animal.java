package com.example.shelter.animal;

import com.example.shelter.box.Box;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private AnimalSpecies species;
    private String name;
    private String sex;
    private String size;
    private Integer age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;
    private String description;
    private Boolean adopted = false;
    private Boolean vaccinated = false;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate vaccinationDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate adoptionDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastWalkDate;

    @ManyToOne
    private Box box;
}
