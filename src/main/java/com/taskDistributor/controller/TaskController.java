package com.taskDistributor.controller;

import com.taskDistributor.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TaskController {

    @Autowired
    public TaskService taskService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/tasks")
    public String getAllTasks(Model model){
        model.addAttribute("tasks", taskService.findAll());
        return "tasksList";
    }
}
