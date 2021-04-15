package com.mary.todolist.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mary.todolist.domain.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonService {

    public void writeToJson(List<Task> tasks, Path path) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String taskList = gson.toJson(tasks);
        try {
            Files.write(path, taskList.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> readFromJson(Path path) {
        List<Task> tasks = new ArrayList<>();
        Gson gson = new Gson();
        try {
            Task[] temp = gson.fromJson(Files.readString(path), Task[].class);
            tasks = new ArrayList<>(Arrays.asList(temp));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}