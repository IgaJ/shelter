package com.example.shelter.mappers;

import com.example.shelter.box.BoxDTO;
import com.example.shelter.box.Box;
import org.mapstruct.Mapper;

@Mapper(uses = AnimalMapper.class)
public interface BoxMapper {
    Box toBox(BoxDTO boxDTO);

    BoxDTO toBoxDTO(Box box);
}
