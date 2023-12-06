package com.example.shelter.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Box {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    private Integer number;
    private Boolean isQuarantine;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime cleaningDate;

    @OneToMany(mappedBy = "box") //mapowane przez drugą stronę (Animal) przez pole o zawie "box" - nie towrzy się druga tabela
    //@JoinTable(name = "animals_in_box", joinColumns = @JoinColumn(name = "box_id"), inverseJoinColumns = @JoinColumn(name = "animal_id"))
    private Set <Animal> animals = new HashSet<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);
        animal.setBox(this);
    }
}
