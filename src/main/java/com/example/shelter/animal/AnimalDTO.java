package com.example.shelter.animal;

import com.example.shelter.action.Action;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class AnimalDTO {

    private Integer id;
    private String name;
    private int boxNumber;

    @NotNull
    private AnimalSpecies species;

    private String sex;
    private String size;
    private Integer age;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;


    private String description;
    private Boolean adopted;
    private Boolean vaccinated;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate vaccinationDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate adoptionDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastWalkDate;

    private Set<Action> actions;
}
