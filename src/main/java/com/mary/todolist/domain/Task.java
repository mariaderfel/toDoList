package com.mary.todolist.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class Task {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Enter date from future")
    @NotNull(message = "Date can't be empty")
    private LocalDate term;
    @Min(1)
    @Max(5)
    private int priority;
    private Category category;

    @NotEmpty(message = "Description can't be empty")
    private String description;

    public Task() {
    }

    public Task(@Future(message = "Enter date from future") @NotNull(message = "Date can't be empty") LocalDate term, @Min(1) @Max(5) int priority, Category category, @NotEmpty(message = "Description can't be empty") String description) {
        this.term = term;
        this.priority = priority;
        this.category = category;
        this.description = description;
    }

    public LocalDate getTerm() {
        return term;
    }

    public int getPriority() {
        return priority;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setTerm(LocalDate term) {
        this.term = term;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "term=" + term +
                ", priority=" + priority +
                ", category=" + category +
                ", description='" + description + '\'' +
                '}';
    }
}
