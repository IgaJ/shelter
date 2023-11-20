package com.example.shelter.dto;

import com.example.shelter.entity.ActionType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class ActionDTO {
<<<<<<< HEAD
=======

>>>>>>> origin/master
    private UUID id;

    private ActionType actionType;

    private LocalDateTime date;
}
