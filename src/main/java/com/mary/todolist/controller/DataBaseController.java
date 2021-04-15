package com.mary.todolist.controller;

import com.mary.todolist.domain.Task;
import com.mary.todolist.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class DataBaseController {

    private final DataBaseService dataBaseService;

    @Autowired
    public DataBaseController(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    @GetMapping("/add_b")
    public String getTask(Model model) {
        model.addAttribute("newTask", new Task());
        model.addAttribute("categories", dataBaseService.getTaskCategories());
        return "add_task_base_form";
    }

    @PostMapping("/add_task_to_base")
    public String addTask(@Valid @ModelAttribute("newTask") Task newTask, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", dataBaseService.getTaskCategories());
            return "add_task_base_form";
        } else {
            dataBaseService.saveTaskInDataBase(newTask);
            return "redirect:/";
        }
    }
}
