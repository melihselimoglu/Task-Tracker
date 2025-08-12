package com.SpringBoot.tasks.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.tasks.domain.dto.TaskDto;
import com.SpringBoot.tasks.mappers.TaskMapper;
import com.SpringBoot.tasks.services.TaskService;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(path = "/task-list/{task_list_id}/tasks")
public class TasksController {
    
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TasksController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDto> listTasks(@PathVariable("task_list_id") UUID taskListId) {
        return taskService.listTasks(taskListId).stream()
                .map(taskMapper::toDto)
                .toList();
    }
    
}
