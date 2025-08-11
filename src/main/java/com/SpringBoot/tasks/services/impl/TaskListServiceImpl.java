package com.SpringBoot.tasks.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.SpringBoot.tasks.domain.entities.TaskList;
import com.SpringBoot.tasks.repositories.TaskListRepository;
import com.SpringBoot.tasks.services.TaskListService;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {

        return taskListRepository.findAll(); 
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (taskList.getId() != null) {
            throw new IllegalArgumentException("TaskList ID must be null for creation");
        }
        if (taskList.getTitle() == null || taskList.getTitle().isBlank()) {
            throw new IllegalArgumentException("TaskList name cannot be null or empty");
        }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
            null,
            taskList.getTitle(),
            taskList.getDescription(),
            null,
            now,
            now
        ));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(null);   
    }

    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if(null == taskList.getId()) {
            throw new IllegalArgumentException("TaskList ID cannot be null");
        }

        if (!Objects.equals(taskListId, taskList.getId())) {
            throw new IllegalArgumentException("TaskList ID in path does not match TaskList ID in body");
        }
        
        TaskList existingTaskList = taskListRepository.findById(taskListId)
            .orElseThrow(() -> new IllegalArgumentException("TaskList not found"));

        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdatedAt(LocalDateTime.now());
        return taskListRepository.save(existingTaskList);
    }

    @Override
    public void deleteTaskList(UUID taskListId) {
        taskListRepository.deleteById(taskListId);
    }
}