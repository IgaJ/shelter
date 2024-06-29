package com.example.shelter.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AnimalRepository extends JpaRepository <Animal, Integer>, JpaSpecificationExecutor<Animal> {
}
