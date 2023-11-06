package com.example.shelter.dto;

import com.example.shelter.entity.AnimalSpecies;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class AnimalDTO {

    private UUID id;

    @NotNull
    private AnimalSpecies species;

    @NotNull
    public String vaccinated;

    private String name;
    private String sex;
    private String size;
    private Integer age;

    @NotNull
    @NotBlank
    private String arrival;

    private Integer box;

    private Integer chip;

    private String adoptionReady;

    private String description;


}
