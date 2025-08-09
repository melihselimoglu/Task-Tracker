package com.SpringBoot.tasks.mappers;

import com.SpringBoot.tasks.domain.dto.TaskDto;
import com.SpringBoot.tasks.domain.entities.Task;

public interface TaskListMapper {
    
    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);

}
 