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
import java.util.*;

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

    @GetMapping("/tasks")
    public String getAllTasks(Model model){
        List<Task>  tasks = taskService.findAll();
        Set <String> assignee= new HashSet<String>();
        for (int i = 0; i < tasks.size(); i++) {
            assignee.add(tasks.get(i).getAssignee());
        }
        model.addAttribute("assignee",assignee);
        model.addAttribute("tasks", tasks);


        return "tasksList";
    }

//    @PostMapping("filterByAssignee")
//    public String filterByAssignee(@RequestParam String assignee, Map<String, Object> model){
//
//
//    }

    @GetMapping("/addTask")
    public String createTaskPage(){
        return "createTask";
    }


    @PostMapping("/addTask")
    public String addTask(@ModelAttribute("task")Task task){
        if (task.getSummary().isEmpty()||task.getAssignee().isEmpty()||task.getEndDate()==null||task.getStartDate()==null){
            return "createTask";
        }else {
        taskService.save(task);
        return "redirect:/tasks";}
    }
}