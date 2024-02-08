package com.example.shelter.mappers;

import com.example.shelter.dto.ActionDTO;
import com.example.shelter.dto.AnimalDTO;
import com.example.shelter.entity.Action;
import com.example.shelter.entity.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = AnimalMapper.class)
public interface ActionMapper {
    Action toAction (ActionDTO actionDTO);
    //ActionDTO toActionDTO (Action action);

    @Mapping(target = "animalId", source = "animal.id") // target to pole w DTO a source to pole w animal
    ActionDTO toActionDTO (Action action);

}
