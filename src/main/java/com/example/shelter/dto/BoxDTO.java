package com.example.shelter.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class BoxDTO {
    private Integer id;

    private Integer boxNumber;
    private Boolean isQuarantine;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime cleaningDate;

    // adnotacja onetomany niepotrzebna
    private Set<AnimalDTO> animals; // zmienną uzupełnia mapper
}
