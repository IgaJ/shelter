package com.example.shelter.dto;

import com.example.shelter.entity.ActionType;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class ActionDTO {

    private UUID id;

    private ActionType actionType;

    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private String actionDate;

    private UUID animalId;

    private UUID boxId;

}
