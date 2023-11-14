package com.example.shelter.mappers;

import com.example.shelter.dto.ActionDTO;
import com.example.shelter.entity.Action;
import org.mapstruct.Mapper;

@Mapper
public interface ActionMapper {
    Action toAction (ActionDTO actionDTO);
    ActionDTO toActionDTO (Action action);
}
