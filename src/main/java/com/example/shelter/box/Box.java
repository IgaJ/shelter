package com.example.shelter.box;

import com.example.shelter.animal.Animal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private Integer boxNumber;
    private Boolean isQuarantine;
    @Builder.Default
    private int maxAnimals = 4;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate cleaningDate;

    @OneToMany(mappedBy = "box", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @JsonIgnore
    private Set<Animal> animals = new HashSet<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);
        animal.setBox(this);
    }
}