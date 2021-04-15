package com.mary.todolist.service;

import com.mary.todolist.domain.Category;
import com.mary.todolist.domain.Task;
import com.mary.todolist.entity.TaskEntity;
import com.mary.todolist.entityRepository.TaskRepository;
import com.mary.todolist.functions.TaskFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataBaseService {

    private final TaskRepository taskRepository;

    @Autowired
    public DataBaseService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void saveTaskInDataBase(Task task) {
        TaskEntity taskEntity = TaskFunctions.taskToTaskEntity.apply(task);
        taskRepository.save(taskEntity);
    }

    public List<Category> getTaskCategories() {
        return Arrays.asList(Category.values());
    }

}
