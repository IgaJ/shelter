package com.example.shelter.repository;

import com.example.shelter.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VolunteerRepository extends JpaRepository <Volunteer, UUID> {
}
