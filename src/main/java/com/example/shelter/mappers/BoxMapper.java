package com.example.shelter.mappers;

import com.example.shelter.dto.BoxDTO;
import com.example.shelter.entity.Box;
import org.mapstruct.Mapper;

@Mapper
public interface BoxMapper {
    Box toBox (BoxDTO boxDTO);
    BoxDTO toBoxDTO (Box box);
}
