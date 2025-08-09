package com.SpringBoot.tasks.mappers;

import com.SpringBoot.tasks.domain.dto.TaskListDto;
import com.SpringBoot.tasks.domain.entities.TaskList;

public interface TaskListMapper {
    
    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);

}
 