package com.example.shelter.mappers;

import com.example.shelter.dto.VolunteerDTO;
import com.example.shelter.entity.Volunteer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface VolunteerMapper {

    Volunteer volunteerDTOToVolunteer(VolunteerDTO dto);
    VolunteerDTO volunteerDTOToVolunteer(Volunteer volunteer);
}
