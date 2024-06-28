package com.example.shelter.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimalRepository extends JpaRepository <Animal, Integer>, JpaSpecificationExecutor<Animal> {

    @Query("SELECT a FROM Animal a WHERE a.vaccinated = :vaccinated") // query z dwoma warunkami?
    List<Animal> getNonVaccinated(Boolean vaccinated);

    @Query("SELECT a FROM Animal a WHERE a.vaccinated = true and a.adopted = false")
    List<Animal> getAvailableForAdoption();
}
