package com.mary.todolist.functions;

import com.mary.todolist.domain.Task;
import com.mary.todolist.entity.TaskEntity;

import java.util.function.Function;

public class TaskFunctions {

    public static final Function<Task, TaskEntity> taskToTaskEntity = task -> new TaskEntity(
            task.getTerm(),
            task.getPriority(),
            task.getCategory(),
            task.getDescription()
    );
}
