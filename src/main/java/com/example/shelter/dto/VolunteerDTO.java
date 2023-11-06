package com.example.shelter.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class VolunteerDTO {

    private UUID id;

    @NotNull
    private String name;
}
