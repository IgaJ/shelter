package com.example.shelter.task;

import com.example.shelter.animal.Animal;
import com.example.shelter.box.Box;
import com.example.shelter.mappers.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public Task saveTaskForAnimal(Animal animal, String description, TaskType taskType) {
        Task task = Task.builder()
                .description(description)
                .date(LocalDate.now())
                .type(taskType)
                .completed(false)
                .animal(animal)
                .build();
        return taskRepository.save(task);
    }

    public Task saveTaskForBox(Box box, String description, TaskType taskType) {
        Task task = Task.builder()
                .description(description)
                .date(LocalDate.now())
                .type(taskType)
                .completed(false)
                .box(box)
                .build();
        return taskRepository.save(task);
    }

    public TaskDTO update(TaskDTO taskDTO) {
        var newTask = taskMapper.toTask(taskDTO);
        return taskMapper.toTaskDTO(taskRepository.save(newTask));
    }

    public boolean delete(Integer taskId) {
        var task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepository.deleteById(taskId);
        return true;
    }

    public List<TaskDTO> listAll() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toTaskDTO)
                .collect(Collectors.toList());
    }

    public List<TaskDTO> findByDate(LocalDate date) {
        return taskRepository.findByDate(date)
                .stream()
                .map(taskMapper::toTaskDTO)
                .collect(Collectors.toList());
    }

    public List<TaskDTO> findByAnimalId(Integer id) {
        return taskRepository.findByAnimalId(id)
                .stream()
                .map(taskMapper::toTaskDTO)
                .collect(Collectors.toList());
    }

    public List<TaskDTO> findByBoxId(Integer id) {
        return taskRepository.findByBoxId(id)
                .stream()
                .map(taskMapper::toTaskDTO)
                .collect(Collectors.toList());
    }

    public List<TaskDTO> findByType(TaskType taskType) {
        return taskRepository.findByType(taskType)
                .stream()
                .map(taskMapper::toTaskDTO)
                .collect(Collectors.toList());
    }
}
