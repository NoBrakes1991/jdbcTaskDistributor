package com.taskDistributor.service;

import com.taskDistributor.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAll();
    void save(Task task);
}
