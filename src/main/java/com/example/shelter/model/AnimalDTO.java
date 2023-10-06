package com.example.shelter.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class AnimalDTO {

    public UUID id;

    @NotNull
    public AnimalSpecies species;

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
