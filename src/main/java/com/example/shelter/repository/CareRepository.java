package com.example.shelter.repository;

import com.example.shelter.entity.Care;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CareRepository extends JpaRepository<Care, UUID> {
}
