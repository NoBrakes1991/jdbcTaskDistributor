package com.taskDistributor.service;

import com.taskDistributor.entity.Task;

import java.util.Date;
import java.util.List;

public interface TaskService {

    List<Task> findAll();
    void save(Task task);
    List<Task> findByAssignee(String assignee);
    List<Task> findByStartDateAndEndDate(Date startDate,Date endDate);
    List<Task> findByAssigneeStartDateAndEndDate(String assignee,Date startDate,Date endDate);

}
