package com.taskDistributor.controller;

import com.taskDistributor.entity.Task;
import com.taskDistributor.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class TaskController {

    @Autowired
    public TaskService taskService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder, Locale locale, HttpServletRequest request) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

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

    @GetMapping("/addTask")
    public String createTaskPage(){
        return "createTask";
    }


    @PostMapping("/addTask")
    public String addTask(@ModelAttribute("task")Task task){
        taskService.save(task);
        return "redirect:/tasks";
    }
}
