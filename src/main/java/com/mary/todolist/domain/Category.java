package com.mary.todolist.domain;

public enum Category {
    WORK("work"),
    HOUSE("house"),
    FAMILY("family"),
    HOBBY("hobby"),
    FRIENDS("friends");

    String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
