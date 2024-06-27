package com.example.shelter.mappers;

import com.example.shelter.animal.Animal;
import com.example.shelter.animal.AnimalDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AnimalMapper {
    Animal toAnimal(AnimalDTO dto);

    @Mapping(target = "boxNumber", source = "box.boxNumber") // @mapping definiuje niestandardowe mapowanie, target to pole w DTO a source to pole w box
    AnimalDTO toAnimalDTO(Animal animal);

}
