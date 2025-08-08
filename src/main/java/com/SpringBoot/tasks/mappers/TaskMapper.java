package com.SpringBoot.tasks.mappers;

import com.SpringBoot.tasks.domain.dto.TaskDto;
import com.SpringBoot.tasks.domain.entities.Task;

public interface TaskMapper {
    
    Task fromDto(TaskDto taskDto);

    TaskDto toDto(Task task);

}
