package com.example.shelter.repository;

import com.example.shelter.entity.Action;
import com.example.shelter.entity.ActionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActionRepository extends JpaRepository <Action, Integer> {

    @Query("SELECT d FROM Action d WHERE d.actionType = :actionType")
    List<Action> listActions(@Param("actionType") ActionType actionType);
}
