package com.example.shelter.entity;

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

    @OneToMany(mappedBy = "box")
    //mapowane przez drugą stronę (Animal) przez pole o zawie "box" - nie towrzy się druga tabela
    //@JoinTable(name = "animals_in_box", joinColumns = @JoinColumn(name = "box_id"), inverseJoinColumns = @JoinColumn(name = "animal_id"))
    private Set<Animal> animals; // czemu nie inicjalizuje przy tworzeniu nowego boxu? Przeniosłąm inicjalzację do bs.saveNewBox

    public void addAnimal(Animal animal) {
        animals.add(animal); // ostatecznie iniciualizacja w bs.saveNewBox. Trzeba było ddoać sprawdzenie w as.saveNewAnimal czy kolekcja jest null i inicjalizację
        animal.setBox(this);
    }

    @OneToMany(mappedBy = "box", cascade = CascadeType.ALL)
    private Set<Action> actions = new HashSet<>();

    public void addAction(Action action) {
        actions.add(action);
    }
}


// Zależności opóźnione (lazy):
//Jeśli relacja animals jest ustawiona na tryb "lazy loading", Hibernate nie załaduje zestawu animals, dopóki nie zostanie użyta ta właściwość. W takim przypadku, upewnij się,
// że w momencie używania animals (np. w metodzie addAnimal lub w innym miejscu) sesja Hibernate jest wciąż aktywna.