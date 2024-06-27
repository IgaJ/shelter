package com.example.shelter.action;

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
public class  Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private ActionType actionType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate actionDate;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "box_id")
    private Box box;

}