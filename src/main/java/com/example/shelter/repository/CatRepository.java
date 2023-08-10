package com.example.shelter.repository;

import com.example.shelter.model.Cat;
import com.example.shelter.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CatRepository extends JpaRepository <Cat, String> {
    // customowa metoda w interfejcie konieczna,bo w defaultowych nie było getByName
    @Query("SELECT d FROM Cat d WHERE LOWER(d.name) = LOWER(:name)")
    List<Cat> getCatByName(@Param("name") String name);
}
