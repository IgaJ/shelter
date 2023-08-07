package com.example.shelter.repository;

import com.example.shelter.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository <Cat, String> {
}
