package com.example.shelter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    private AnimalSpecies species;

    private String name;
    private String sex;
    private String size;
    private Integer age;

    private Date arrivalDate;

    private String description;
    private Boolean adopted;
    private Boolean vaccinated;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime vaccinationDate;
    private LocalDateTime lastWalkDate;

    private Box box;
    private Set<Action> actions = new HashSet<>();
}
