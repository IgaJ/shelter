package com.example.shelter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VolunteerDTO {
    private Integer id;

    @NotNull
    @NotBlank
    private String name;
}
