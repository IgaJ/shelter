package com.example.shelter.mappers;

import com.example.shelter.task.Task;
import com.example.shelter.task.TaskDTO;
import org.mapstruct.Mapper;

@Mapper
public interface TaskMapper {
    Task toTask (TaskDTO dto);
    TaskDTO toTaskDTO(Task task);

}
