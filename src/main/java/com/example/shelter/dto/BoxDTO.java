package com.example.shelter.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class BoxDTO {
    private UUID id;

    private Integer number; // 0 = kwarantanna

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime cleaningDate;

}
