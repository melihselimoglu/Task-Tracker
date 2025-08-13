package com.SpringBoot.tasks.services;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

import com.SpringBoot.tasks.domain.entities.Task;

public interface TaskService {
    List<Task> listTasks(UUID taskListId);
    Task createTask(UUID taskListId, Task task);
    Optional<Task> getTask(UUID taskListId, UUID taskId);
}
