package com.example.shelter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
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

    @ManyToOne
    private Box box;

    private String description;
    private Boolean adopted = false;
    private Boolean vaccinated = false;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate adoptionDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastWalkDate;

    @OneToMany (mappedBy = "animal", cascade = CascadeType.ALL)
    private Set<Action> actions = new HashSet<>();

    public void addAction(Action action) {
        actions.add(action);
    }

}
