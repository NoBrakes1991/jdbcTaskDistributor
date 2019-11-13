package com.taskDistributor.controller;

import com.taskDistributor.entity.Task;
import com.taskDistributor.service.EndDayReplace;
import com.taskDistributor.service.MessageSelectedFilter;
import com.taskDistributor.service.StartDayReplace;
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
public class TaskController {

    @Autowired
    public TaskService taskService;
    static Set<String> uniqAssignee = new HashSet<String>();

    @InitBinder
    public void initBinder(WebDataBinder dataBinder, Locale locale, HttpServletRequest request) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        List<Task> tasks = taskService.findAll();

        for (int i = 0; i < tasks.size(); i++) {
            uniqAssignee.add(tasks.get(i).getAssignee());
        }
        model.addAttribute("uniqAssignee", uniqAssignee);
        model.addAttribute("tasks", tasks);

        return "index";
    }

    @RequestMapping(value = {"/createTask"}, method = RequestMethod.GET)
    public String viewCreateTask() {
        return "createTask";
    }

    @PostMapping("add")
    public String add(@RequestParam String summary, @RequestParam String assignee, @RequestParam Date startDate, @RequestParam Date endDate, Map<String, Object> model) {
        if (startDate == null || endDate == null || assignee.isEmpty() || summary.isEmpty() || startDate.after(endDate)) {
            model.put("message", "You choose incorrect parameters! Please, check: 1)All fields is not Empty; 2) End date >= Start date.");
            return "createTask";
        } else {
            Task task = new Task(summary, startDate, endDate, assignee);
            System.out.println(summary + " " + assignee + " " + startDate + " " + endDate);
            System.out.println(task.getAssignee());
            taskService.save(task);

            Iterable<Task> tasks = taskService.findAll();
            uniqAssignee.add(task.getAssignee());
            model.put("uniqAssignee", uniqAssignee);
            model.put("tasks", tasks);
            return "redirect:/index";
        }
    }
//    @PostMapping("filterByAssignee")
//    public String filterByAssignee(@RequestParam String assignee, Map<String, Object> model) {
//        List<Task> tasks;
//
//        if (assignee != null && !assignee.isEmpty()) {
//            tasks = taskService.findByAssignee(assignee);
//        } else {
//            tasks = taskService.findAll();
//        }
//
//        model.put("tasks", tasks);
//        model.put("uniqAssignee", uniqAssignee);
//        return "index";
//    }

    @PostMapping("filterByDateAndAssignee")
    public String filterDateAndAssignee(@RequestParam Date startDate, @RequestParam Date endDate, @RequestParam String period, @RequestParam String assignee, Map<String, Object> model) {
        Iterable<Task> tasks;

        if (!period.equals("")) {
            startDate = StartDayReplace.getDate(period);
            endDate = EndDayReplace.getDate(period);
        }

        if (startDate == null && endDate == null && !assignee.isEmpty()) {
            tasks = taskService.findByAssignee(assignee);
//        } else if (startDate != null && endDate != null && !assignee.isEmpty()) {
//            tasks = taskService.findByAssigneeAndStartDateBeforeAndEndDateAfterOrAssigneeAndStartDateOrAssigneeAndEndDate(assignee, endDate, startDate, assignee, startDate, assignee, endDate);
//        } else if (startDate != null && endDate != null && assignee.isEmpty()) {
//            tasks = taskService.findByStartDateBeforeAndEndDateAfterOrStartDateOrEndDate(endDate, startDate, endDate, startDate);
        } else {
            tasks = taskService.findAll();
        }

        if (!tasks.iterator().hasNext()) {
            model.put("messageNotFound", "Not found by your filter");
        }

        model.put("messageSelectedFilter", MessageSelectedFilter.getMessageSelectedFilter(assignee, startDate, endDate));
        model.put("uniqAssignee", uniqAssignee);
        model.put("tasks", tasks);
        return "index";
    }
}