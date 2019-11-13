package com.taskDistributor.service;

import com.taskDistributor.dao.TaskDao;
import com.taskDistributor.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    public TaskDao taskDao;

    public List<Task> findAll() {
        return taskDao.findAll();
    }
}
