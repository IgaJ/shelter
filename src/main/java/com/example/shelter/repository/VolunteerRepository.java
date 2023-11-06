package com.example.shelter.repository;

import com.example.shelter.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface VolunteerRepository extends JpaRepository <Volunteer, UUID> {

    @Query("SELECT d FROM Volunteer d WHERE LOWER(d.name) = LOWER(:name)")
    List<Volunteer> getVolunteerByName(@Param("name") String name);
}
