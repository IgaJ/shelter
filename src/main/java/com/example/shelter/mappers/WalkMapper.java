package com.example.shelter.mappers;

import com.example.shelter.dto.WalkDTO;
import com.example.shelter.entity.Walk;
import org.mapstruct.Mapper;

@Mapper
public interface WalkMapper {
    Walk toWalk (WalkDTO walkDTO);
    WalkDTO toWalkDTO (Walk walk);
}
