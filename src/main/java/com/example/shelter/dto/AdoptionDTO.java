package com.example.shelter.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
public class AdoptionDTO {
    private UUID id;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate adoptionDate;

    private Boolean isReady;

}
