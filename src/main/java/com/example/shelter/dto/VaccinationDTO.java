package com.example.shelter.dto;

import com.example.shelter.entity.Animal;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
public class VaccinationDTO {

    private UUID id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;
    


}
