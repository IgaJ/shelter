package com.example.shelter.repository;

import com.example.shelter.entity.Action;
import com.example.shelter.entity.ActionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActionRepository extends JpaRepository <Action, UUID> {
<<<<<<< HEAD
    @Query("SELECT d FROM Action d WHERE d.actionType = :actionType")
    List<Action> listActions(@Param("actionType") ActionType actionType);
=======

    @Query("SELECT d FROM Action d WHERE d.actionType = :actionType")
    List<Action> listActions(@Param("actionType") ActionType actionType);


>>>>>>> origin/master
}
