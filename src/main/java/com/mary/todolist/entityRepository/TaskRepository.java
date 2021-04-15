package com.mary.todolist.entityRepository;

import com.mary.todolist.dao.GenericDao;
import com.mary.todolist.entity.TaskEntity;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository extends GenericDao<TaskEntity> {
}
