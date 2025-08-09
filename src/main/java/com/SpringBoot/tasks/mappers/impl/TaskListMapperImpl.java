package com.SpringBoot.tasks.mappers.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Component;

import com.SpringBoot.tasks.domain.dto.TaskListDto;
import com.SpringBoot.tasks.domain.entities.Task;
import com.SpringBoot.tasks.domain.entities.TaskList;
import com.SpringBoot.tasks.domain.entities.TaskStatus;
import com.SpringBoot.tasks.mappers.TaskListMapper;
import com.SpringBoot.tasks.mappers.TaskMapper;

@Component
public class TaskListMapperImpl implements TaskListMapper {


    private final TaskMapper taskMapper;
    
    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto)
                                .toList()
                        ).orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
            taskList.getId(),
            taskList.getTitle(),
            taskList.getDescription(),
            Optional.ofNullable(taskList.getTasks())
                    .map(List::size)
                    .orElse(0),
            calculateTaskListProgress(taskList.getTasks()),
            Optional.ofNullable(taskList.getTasks())
                    .map(tasks -> tasks.stream()
                            .map(taskMapper::toDto)
                            .toList()
                    ).orElse(null)
        );
    }
    private Double calculateTaskListProgress(List<Task> tasks) {
        if(null == tasks ) {
            return null;
        }

        long closedTaskCount =tasks.stream()
            .filter(task -> TaskStatus.CLOSED == task.getStatus())
            .count();

        return (double) closedTaskCount / tasks.size();
    }
}
