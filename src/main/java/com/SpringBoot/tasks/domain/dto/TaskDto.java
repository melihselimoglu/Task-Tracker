package com.SpringBoot.tasks.domain.dto;

import java.util.UUID;
import java.time.LocalDateTime;
import com.SpringBoot.tasks.domain.entities.TaskPriority;
import com.SpringBoot.tasks.domain.entities.TaskStatus;

public record TaskDto(
        UUID id, 
        String title, 
        String description, 
        LocalDateTime dueDate, 
        TaskPriority priority, 
        TaskStatus status) {

	
}
