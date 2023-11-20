package com.example.shelter.dto;

import com.example.shelter.entity.AnimalSpecies;
import com.example.shelter.entity.Box;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class AnimalDTO {

    private UUID id;

    @NotNull
    private AnimalSpecies species;

    private String name;
    private String sex;
    private String size;
    private Integer age;

    private Date arrivalDate;

    private String description;
    private Boolean adopted;
    private Boolean vaccinated;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime vaccinationDate;
    private LocalDateTime lastWalkDate;

    private Box box;

}
