package com.SpringBoot.tasks.services;

import java.util.List;

import com.SpringBoot.tasks.domain.entities.TaskList;

public interface TaskListService {
    List<TaskList> listTaskLists();
    TaskList createTaskList(TaskList taskList);
}
