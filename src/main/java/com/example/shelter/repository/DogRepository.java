package com.example.shelter.repository;

import com.example.shelter.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends JpaRepository<Dog, Integer> {
    // customowa metoda w interfejcie konieczna,bo w defaultowych nie było getByName

    @Query("SELECT d FROM Dog d WHERE LOWER(d.name) = LOWER(:name)")
    List<Dog> getDogByName(@Param("name") String name);
    }

