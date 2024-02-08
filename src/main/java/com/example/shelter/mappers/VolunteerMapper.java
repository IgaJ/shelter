package com.example.shelter.mappers;

import com.example.shelter.dto.VolunteerDTO;
import com.example.shelter.entity.Volunteer;
import org.mapstruct.Mapper;

@Mapper
public interface VolunteerMapper {

    Volunteer volunteerDTOToVolunteer(VolunteerDTO dto);
    VolunteerDTO volunteerDTOToVolunteer(Volunteer volunteer);
}
