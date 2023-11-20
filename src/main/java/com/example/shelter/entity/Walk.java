package com.example.shelter.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Walk {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate walkDate;

    @ManyToOne
    @JoinTable(name = "walks_animals", joinColumns = @JoinColumn(name = "walk_id"), inverseJoinColumns = @JoinColumn(name = "animal_id"))
    private Animal animal;

    @ManyToOne
    @JoinTable(name = "walks_volunteers", joinColumns = @JoinColumn(name = "walk_id"), inverseJoinColumns = @JoinColumn(name = "volunteer_id"))
    private Volunteer volunteer;

}
