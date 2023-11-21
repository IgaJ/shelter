package com.example.shelter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
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
    @JdbcTypeCode(SqlTypes.CHAR)
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

    @ManyToOne
    @JoinTable(name = "animals_in_box", joinColumns = @JoinColumn(name = "animal_id"), inverseJoinColumns = @JoinColumn(name = "box_id"))
    private Box box;

    @ManyToMany
    private Set<Action> actions = new HashSet<>();

    public void addBox(Box box) {
        this.box = box;
        box.getAnimals().add(this);

    }

/*
    @ManyToMany
    @Builder.Default
    @JoinTable(name = "care_treatments", joinColumns = @JoinColumn(name = "animal_id"), inverseJoinColumns = @JoinColumn(name = "care_id"))
    private Set<Care> careTreatments = new HashSet<>();
*/

}
