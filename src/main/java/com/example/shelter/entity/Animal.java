package com.example.shelter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.h2.api.UserToRolesMapper;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;
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

    private String box;

    private String description;
    private Boolean adopted;
    private Boolean vaccinated;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate vaccinationDate;

    @OneToMany
    @JoinTable(name = "vaccinations", joinColumns = @JoinColumn(name = "animal_id"), inverseJoinColumns = @JoinColumn(name = "vaccination_id"))
    private Set<Vaccination> vaccinations = new HashSet<>();


/*
    @ManyToMany
    @Builder.Default
    @JoinTable(name = "care_treatments", joinColumns = @JoinColumn(name = "animal_id"), inverseJoinColumns = @JoinColumn(name = "care_id"))
    private Set<Care> careTreatments = new HashSet<>();
*/




}
