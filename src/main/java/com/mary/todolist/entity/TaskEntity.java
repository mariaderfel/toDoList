package com.mary.todolist.entity;

import com.mary.todolist.domain.Category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class TaskEntity {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate term;
    private int priority;
    private Category category;
    private String description;

    public TaskEntity() {
    }

    public TaskEntity(LocalDate term, int priority, Category category, String description) {
        this.term = term;
        this.priority = priority;
        this.category = category;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getTerm() {
        return term;
    }

    public void setTerm(LocalDate term) {
        this.term = term;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id=" + id +
                ", term=" + term +
                ", priority=" + priority +
                ", category=" + category +
                ", description='" + description + '\'' +
                '}';
    }
}
