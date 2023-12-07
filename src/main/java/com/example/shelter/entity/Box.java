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

    @Column(unique = true) // czy to coś w ogóle robi skoro numer jest nadawany automatycznie?
    private Integer boxNumber;

    private Boolean isQuarantine;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime cleaningDate;

    @OneToMany(mappedBy = "box") //mapowane przez drugą stronę (Animal) przez pole o zawie "box" - nie towrzy się druga tabela
    //@JoinTable(name = "animals_in_box", joinColumns = @JoinColumn(name = "box_id"), inverseJoinColumns = @JoinColumn(name = "animal_id"))
    private Set <Animal> animals; // czemu nie inicjalizuje przy tworzeniu nowego boxu? Przeniosłąm inicjalzację do bs.saveNewBox

    public void addAnimal(Animal animal) {
        animals.add(animal); // ostatecznie iniciualizacja w bs.saveNewBox. Trzeba było ddoać sprawdzenie w as.saveNewAnimal czy kolekcja jest null i inicjalizację
        animal.setBox(this);
    }
}
// Zależności opóźnione (lazy):
//Jeśli relacja animals jest ustawiona na tryb "lazy loading", Hibernate nie załaduje zestawu animals, dopóki nie zostanie użyta ta właściwość. W takim przypadku, upewnij się,
// że w momencie używania animals (np. w metodzie addAnimal lub w innym miejscu) sesja Hibernate jest wciąż aktywna.