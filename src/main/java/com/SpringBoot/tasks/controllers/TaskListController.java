package com.SpringBoot.tasks.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.tasks.domain.dto.TaskListDto;
import com.SpringBoot.tasks.domain.entities.TaskList;
import com.SpringBoot.tasks.mappers.TaskListMapper;
import com.SpringBoot.tasks.services.TaskListService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> listTaskLists() {
        return taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
    }
    
    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskList createdTaskList=taskListService.createTaskList(
            taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(createdTaskList); 
    }
    
    @GetMapping(path="/{task_list_id}")
    public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID taskListid) {
        return taskListService.getTaskList(taskListid)
                .map(taskListMapper::toDto);
    }
}
