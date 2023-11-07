package com.example.shelter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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

    @NotNull
    public String vaccinated;

    private String name;
    private String sex;
    private String size;
    private Integer age;

    @NotNull
    @NotBlank
    private String arrival;

    private Integer box;

    private Integer chip;

    private String adoptionReady;

    private String description;

    @ManyToMany
    @Builder.Default
    @JoinTable(name = "care_treatments", joinColumns = @JoinColumn(name = "animal_id"), inverseJoinColumns = @JoinColumn(name = "care_id"))
    private Set<Care> careTreatments = new HashSet<>();




}
