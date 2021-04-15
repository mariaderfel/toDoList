package com.mary.todolist.domain;

import javax.validation.constraints.NotEmpty;

public class Description {
    @NotEmpty(message = "Description can't be empty")
    private String description;

    public Description() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
