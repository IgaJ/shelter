package com.example.shelter.repository;

import com.example.shelter.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VolunteerRepository extends JpaRepository <Volunteer, UUID> {
}
