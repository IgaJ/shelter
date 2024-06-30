package com.example.shelter.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository  extends JpaRepository<Task, Integer> {
    List<Task> findByAnimalId(Integer id);
    List<Task> findByBoxId(Integer id);
    List<Task> findByDate(LocalDate date);
    List<Task> findByType(TaskType taskType);


}

