package com.example.shelter.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
public class Care {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    private LocalDateTime vaccination;

    private LocalDateTime careDate;

    private String careDescription;

    private LocalDateTime vetVisit;

    private String visitDescription;

    @ManyToMany
    @JoinTable(name = "animals_care", joinColumns = @JoinColumn(name = "care_id"), inverseJoinColumns = @JoinColumn(name = "animal_id"))
    @Builder.Default
    private Set<Animal> animals = new HashSet<>();

}
