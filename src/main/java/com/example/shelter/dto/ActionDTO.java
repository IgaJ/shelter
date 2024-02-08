package com.example.shelter.dto;

import com.example.shelter.entity.ActionType;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
public class ActionDTO {

    private Integer id;

    private ActionType actionType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate actionDate;

    private Integer animalId;

    private Integer boxId;

}
