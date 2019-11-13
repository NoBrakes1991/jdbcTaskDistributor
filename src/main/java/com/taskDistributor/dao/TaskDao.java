package com.taskDistributor.dao;

import com.taskDistributor.entity.Task;

import java.util.List;

public interface TaskDao {

    void save(Task task);

    List<Task> findAll();

}
