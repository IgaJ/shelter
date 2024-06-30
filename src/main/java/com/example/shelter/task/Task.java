package com.example.shelter.task;

import com.example.shelter.animal.Animal;
import com.example.shelter.box.Box;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private TaskType type;
    private Boolean completed = false;

    @ManyToOne
    private Box box;
    @ManyToOne
    private Animal animal;
}
