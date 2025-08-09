package com.SpringBoot.tasks.services.impl;

import java.util.List;

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
}
