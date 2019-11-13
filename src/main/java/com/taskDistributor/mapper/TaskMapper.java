package com.taskDistributor.mapper;

import com.taskDistributor.entity.Task;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet resultSet, int i) throws SQLException {
        Task task = new Task();
        task.setId(resultSet.getInt("id"));
        task.setSummary(resultSet.getString("summary"));
        task.setAssignee(resultSet.getString("assignee"));
        task.setStartDate(resultSet.getDate("startDate"));
        task.setEndDate(resultSet.getDate("endDate"));
        return task;
    }
}

