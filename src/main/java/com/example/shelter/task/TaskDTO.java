package com.example.shelter.task;

import com.example.shelter.animal.Animal;
import com.example.shelter.box.Box;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TaskDTO {
    private Integer id;
    private String description;
    private LocalDate date;
    private TaskType type;
    private Boolean completed;
    private Box box;
    private Animal animal;
}
