package com.example.shelter.animal;

import com.example.shelter.action.Action;
import com.example.shelter.box.Box;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private AnimalSpecies species;

    private String name;
    private String sex;
    private String size;
    private Integer age;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;

    private String description;
    private Boolean adopted = false;
    private Boolean vaccinated = false;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate vaccinationDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate adoptionDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastWalkDate;

    @ManyToOne
    private Box box;

    @OneToMany (mappedBy = "animal", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // operacje takie jak zapis, aktualizacja
    // i usuwanie dla Animal będą miały odzwierciedlenie na powiązanych z Animal encjach Action
    @JsonIgnore // dla uniknięcia nieskończonej rekurencji w wyniku cyklicznych relacji między encjami Box a Animal
    private Set<Action> actions = new HashSet<>();

    public void addAction(Action action) {
        actions.add(action);
    }

}
