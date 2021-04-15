package com.mary.todolist.controller;

import com.mary.todolist.domain.Category;
import com.mary.todolist.domain.Description;
import com.mary.todolist.domain.Task;
import com.mary.todolist.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ToDoListController {

    private final ToDoListService toDoListService;

    @Autowired
    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    @GetMapping("/")
    public String printToDoList(Model model) {
        model.addAttribute("tasks", toDoListService.getToDoList());
        return "index";
    }

    //1
    @GetMapping("/highest-priority")
    public String printTasksWithHighestPriority(Model model) {
        model.addAttribute("tasks", toDoListService.getTaskWithHighestPriority());
        return "index";
    }

    //2
    @GetMapping("/next-day")
    public String printTasksForNextDay(Model model) {
        model.addAttribute("tasks", toDoListService.getTaskForNextDay());
        return "index";
    }

    //3
    @GetMapping("/by-priority")
    public String printTasksSortedByPriority(Model model) {
        model.addAttribute("tasks", toDoListService.getTaskSortedByPriority());
        return "index";
    }

    //4
    @GetMapping("/by-date")
    public String printTasksSortedByDate(Model model) {
        model.addAttribute("tasks", toDoListService.getTaskSortedByDate());
        return "index";
    }

    //5
    @GetMapping("/category")
    public String getCategory(Model model) {
        model.addAttribute("newTask", new Task());
        model.addAttribute("categories", toDoListService.getTaskCategories());
        return "by-category";
    }

    @PostMapping("/category")
    public String printTaskByChooseCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", toDoListService.getTaskCategories());
            return "by-category";
        } else {
            model.addAttribute("tasks", toDoListService.getTaskByChooseCategory(category));
            return "index";
        }
    }

    //6
    @GetMapping("/description")
    public String getDescription(Model model) {
        model.addAttribute("description", new Description());
        return "by-description";
    }

    @PostMapping("/description")
    public String printTaskWithDescription(@Valid @ModelAttribute("description") Description description, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "by-description";
        } else {
            model.addAttribute("tasks", toDoListService.getTaskByDescription(description));
            return "index";
        }
    }

    //7
    @GetMapping("/urgent")
    public String printUrgentTasks(Model model) {
        model.addAttribute("tasks", toDoListService.getMostUrgentTask());
        return "index";
    }

    //8
    @GetMapping("/by-category-map")
    public String printTasksSortedByCategory(Model model) {
        model.addAttribute("tasks", toDoListService.getTaskSortedByCategory());
        return "map";
    }

    //9
    @GetMapping("/by-priority-map")
    public String printTasksByPriority(Model model) {
        model.addAttribute("tasks", toDoListService.getTaskByPriority());
        return "map";
    }

    //10
    @GetMapping("/highest-priority-for-category-map")
    public String printHighestPriorityForCategory(Model model) {
        model.addAttribute("tasks", toDoListService.getHighestPriorityForCategory());
        return "map-optional";
    }
}
