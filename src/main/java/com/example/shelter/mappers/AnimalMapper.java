package com.example.shelter.mappers;

import com.example.shelter.animal.Animal;
import com.example.shelter.animal.AnimalDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AnimalMapper {
    Animal toAnimal(AnimalDTO dto);

    @Mapping(target = "boxNumber", source = "box.boxNumber")
    AnimalDTO toAnimalDTO(Animal animal);

}
