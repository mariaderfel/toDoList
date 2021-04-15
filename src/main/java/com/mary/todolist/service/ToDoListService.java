package com.mary.todolist.service;

import com.mary.todolist.domain.Category;
import com.mary.todolist.domain.Description;
import com.mary.todolist.domain.Task;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ToDoListService {

    private List<Task> tasks = new ArrayList<>();
    private final Path PATH = Paths.get("./src/main/java/com/mary/todolist/repository/task.json");
    private final int HIGHEST_PRIORITY = 5;

    public List<Task> getToDoList() {
        tasks = getTaskFromFile();
        return tasks;
    }

    public void addTaskToDoList(Task task) {
        tasks.add(task);
    }

    public List<Category> getTaskCategories() {
        return Arrays.asList(Category.values());
    }

    public void saveTaskInFile() {
        JsonService jsonService = new JsonService();
        jsonService.writeToJson(tasks, PATH);
    }

    public List<Task> getTaskFromFile() {
        JsonService jsonService = new JsonService();
        tasks = jsonService.readFromJson(PATH);
        return tasks;
    }

    public void tearDownToDoList() {
        tasks.clear();
    }

    //1
    public List<Task> getTaskWithHighestPriority() {
        return tasks.stream()
                .filter((Task task) -> task.getPriority() == HIGHEST_PRIORITY)
                .collect(Collectors.toList());
    }

    //2
    public List<Task> getTaskForNextDay() {
        LocalDate nextDay = LocalDate.now().plusDays(1);
        return tasks.stream()
                .filter((Task task) -> task.getTerm().equals(nextDay))
                .collect(Collectors.toList());
    }

    //3
    public List<Task> getTaskSortedByPriority() {
        return tasks.stream()
                .sorted((Task t1, Task t2) -> t2.getPriority() - t1.getPriority())
                .collect(Collectors.toList());
    }

    //4
    public List<Task> getTaskSortedByDate() {
        return tasks.stream()
                .sorted((Task t1, Task t2) -> t1.getTerm().compareTo(t2.getTerm()))
                .collect(Collectors.toList());
    }

    //5
    public List<Task> getTaskByChooseCategory(Category category) {
        return tasks.stream()
                .filter((Task task) -> category.equals(task.getCategory()))
                .collect(Collectors.toList());
    }

    //6
    public List<Task> getTaskByDescription(Description description) {
        String desc = description.getDescription();
        return tasks.stream()
                .filter((Task task) -> task.getDescription().toLowerCase().contains(desc.toLowerCase()))
                .collect(Collectors.toList());
    }

    //7
    public List<Task> getMostUrgentTask() {
        LocalDate urgentDate;
        List<Task> sortedByDateTask = tasks.stream()
                .sorted(Comparator.comparing(Task::getTerm))
                .collect(Collectors.toList());
        try {
            urgentDate = sortedByDateTask.get(0).getTerm();
            LocalDate finalUrgentDate = urgentDate;
            return sortedByDateTask.stream()
                    .filter((Task task) -> finalUrgentDate.isEqual(task.getTerm()))
                    .sorted((Task t1, Task t2) -> t2.getPriority() - t1.getPriority())
                    .limit(1)
                    .collect(Collectors.toList());
        } catch (IndexOutOfBoundsException e) {
            return tasks;
        }
    }

    //8
    public Map<Category, List<Task>> getTaskSortedByCategory() {
        Map<Category, List<Task>> byCategories = new HashMap<>();
        for (int i = 0; i < Category.values().length; i++) {
            Category cat = Category.values()[i];
            List<Task> fitTasks = tasks.stream()
                    .filter((Task task) -> cat.equals(task.getCategory()))
                    .collect(Collectors.toList());
            byCategories.put(Category.values()[i], fitTasks);
        }
        return byCategories;
    }

    //9
    public Map<Integer, List<Task>> getTaskByPriority() {
        Map<Integer, List<Task>> byPriority = new HashMap<>();
        int priorityMin = 1;
        int priorityMax = 5;

        for (int i = priorityMin; i <= priorityMax; i++) {
            int priority = i;
            List<Task> fitTasks = tasks.stream()
                    .filter((Task task) -> priority == task.getPriority())
                    .collect(Collectors.toList());
            byPriority.put(priority, fitTasks);
        }
        return byPriority;
    }

    //10
    public Map<Category, Optional<Task>> getHighestPriorityForCategory() {
        Map<Category, Optional<Task>> highestPriority = new HashMap<>();
        Optional<Task> optionalTask;
        int priorityMax = 5;

        for (int i = 0; i < Category.values().length; i++) {
            Category cat = Category.values()[i];
            List<Task> fitTasks = tasks.stream()
                    .filter((Task task) -> cat.equals(task.getCategory()))
                    .filter((Task task) -> priorityMax == task.getPriority())
                    .collect(Collectors.toList());
            if (fitTasks.isEmpty()) {
                optionalTask = Optional.empty();
            } else {
                optionalTask = Optional.of(fitTasks.get(0));
            }
            highestPriority.put(cat, optionalTask);
        }
        return highestPriority;
    }
}