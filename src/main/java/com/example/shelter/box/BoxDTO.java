package com.example.shelter.box;

import com.example.shelter.animal.AnimalDTO;
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
    private Set<AnimalDTO> animals;
}
