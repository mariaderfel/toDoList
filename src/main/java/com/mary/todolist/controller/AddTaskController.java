package com.mary.todolist.controller;

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
public class AddTaskController {
    private final ToDoListService toDoListService;

    @Autowired
    public AddTaskController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    @GetMapping("/add")
    public String getTask(Model model) {
        model.addAttribute("newTask", new Task());
        model.addAttribute("categories", toDoListService.getTaskCategories());
        return "add_task_form";
    }

    @PostMapping("/add_task")
    public String addTask(@Valid @ModelAttribute("newTask") Task newTask, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", toDoListService.getTaskCategories());
            return "add_task_form";
        } else {
            toDoListService.addTaskToDoList(newTask);
            toDoListService.saveTaskInFile();
            return "redirect:/";
        }
    }
}
