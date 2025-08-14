package com.SpringBoot.tasks.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.SpringBoot.tasks.domain.entities.Task;
import com.SpringBoot.tasks.domain.entities.TaskList;
import com.SpringBoot.tasks.domain.entities.TaskPriority;
import com.SpringBoot.tasks.domain.entities.TaskStatus;
import com.SpringBoot.tasks.repositories.TaskListRepository;
import com.SpringBoot.tasks.repositories.TaskRepository;
import com.SpringBoot.tasks.services.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = null;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        
        return taskRepository.findByTaskListId(taskListId); 
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if(null != task.getId()){
            throw new IllegalArgumentException("Task already has an ID");
        }
        if (null == task.getTitle() || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title cannot be null or blank");
        }

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList=taskListRepository.findById(taskListId)
            .orElseThrow(() -> new IllegalArgumentException("Task list not found"));

        LocalDateTime now = LocalDateTime.now();
        Task taskToSave = new Task( 
            null,
            task.getTitle(),
            task.getDescription(),
            task.getDueDate(),
            taskStatus,
            taskPriority,
            taskList,
            now,
            now
        );  
        
        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(null == task.getId()) {
            throw new IllegalArgumentException("Task must have an ID");
        }
        if (!Objects.equals(taskId, task.getId())){
            throw new IllegalArgumentException("Task ID in path variable and request body must match");
        }
        if (null == task.getPriority()) {
            throw new IllegalArgumentException("Task priority cannot be null");
        }
        if (null == task.getStatus()) {
            throw new IllegalArgumentException("Task status cannot be null");
        }

        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId)
            .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }
}
