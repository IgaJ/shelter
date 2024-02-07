package com.example.shelter.repository;

import com.example.shelter.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository <Volunteer, Integer> {
}
