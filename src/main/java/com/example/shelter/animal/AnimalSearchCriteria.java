package com.example.shelter.animal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnimalSearchCriteria {
    private Integer id;
    private AnimalSpecies species;
    private String name;
    private String sex;
    private String size;
    private Integer age;
    private Boolean adopted;
    private Boolean vaccinated;
}
