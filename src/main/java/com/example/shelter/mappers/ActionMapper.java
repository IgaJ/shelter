package com.example.shelter.mappers;

import com.example.shelter.action.ActionDTO;
import com.example.shelter.action.Action;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = AnimalMapper.class)
public interface ActionMapper {
    Action toAction (ActionDTO actionDTO);

    @Mapping(target = "animalId", source = "animal.id") // target to pole w DTO a source to pole w animal
    ActionDTO toActionDTO (Action action);

}
