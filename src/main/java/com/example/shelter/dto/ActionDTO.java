package com.example.shelter.dto;

import com.example.shelter.entity.ActionType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class ActionDTO {

    private UUID id;

    private ActionType actionType;

    private LocalDateTime actionDate;

    private UUID animalId;

    private UUID boxId;

}
