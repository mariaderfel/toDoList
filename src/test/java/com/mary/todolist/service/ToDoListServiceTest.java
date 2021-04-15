package com.mary.todolist.service;

import com.mary.todolist.domain.Category;
import com.mary.todolist.domain.Description;
import com.mary.todolist.domain.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class ToDoListServiceTest {

    private final ToDoListService toDoListService = new ToDoListService();

    @AfterEach
    void tearDown() {
        toDoListService.tearDownToDoList();
    }

    //1
    @Test
    public void testIfGetTaskWithHighestPriority() {
        //given
        Task task1 = new Task(LocalDate.now(), 5, Category.WORK, "meeting");
        Task task2 = new Task(LocalDate.now(), 2, Category.HOBBY, "crocheting");
        Task task3 = new Task(LocalDate.now(), 5, Category.FRIENDS, "party");
        toDoListService.addTaskToDoList(task1);
        toDoListService.addTaskToDoList(task2);
        toDoListService.addTaskToDoList(task3);
        //when
        List<Task> result = toDoListService.getTaskWithHighestPriority();
        //then
        assertEquals(2, result.size());
    }

    @Test
    public void testIfGetEmptyListIfToDoListIsEmpty() {
        //given
        toDoListService.tearDownToDoList();
        //when
        List<Task> result = toDoListService.getTaskWithHighestPriority();
        //then
        assertEquals(0, result.size());
    }

    //2
    @Test
    public void testIfGetTaskForNextDay() {
        //given
        Task task1 = new Task(LocalDate.now().plusDays(1), 5, Category.WORK, "meeting");
        Task task2 = new Task(LocalDate.now(), 2, Category.HOBBY, "crocheting");
        Task task3 = new Task(LocalDate.now().plusDays(1), 5, Category.FRIENDS, "party");
        toDoListService.addTaskToDoList(task1);
        toDoListService.addTaskToDoList(task2);
        toDoListService.addTaskToDoList(task3);
        //when
        List<Task> result = toDoListService.getTaskForNextDay();
        //then
        assertEquals(2, result.size());
    }

    @Test
    public void testIfGetEmptyListIfNoTaskForNextDay() {
        //given
        Task task1 = new Task(LocalDate.now().plusDays(3), 5, Category.WORK, "meeting");
        toDoListService.addTaskToDoList(task1);
        //when
        List<Task> result = toDoListService.getTaskForNextDay();
        //then
        assertTrue(result.isEmpty());
    }

    //3
    @Test
    public void testIfTaskCanBeSortedByPriority() {
        //given
        Task task1 = new Task(LocalDate.now().plusDays(1), 2, Category.WORK, "meeting");
        Task task2 = new Task(LocalDate.now(), 5, Category.HOBBY, "crocheting");
        toDoListService.addTaskToDoList(task1);
        toDoListService.addTaskToDoList(task2);
        //when
        List<Task> result = toDoListService.getTaskSortedByPriority();
        //then
        assertThat(result.get(0).getPriority() > result.get(1).getPriority()).isTrue();
    }

    @Test
    public void testIfGetEmptyListIfTrySortedEmptyToDoListByPriority() {
        //given
        toDoListService.tearDownToDoList();
        //when
        List<Task> result = toDoListService.getTaskSortedByPriority();
        //then
        assertTrue(result.isEmpty());
    }

    //4
    @Test
    public void testIfGetListSortedByDate() {
        //given
        Task task1 = new Task(LocalDate.now().plusDays(5), 2, Category.WORK, "meeting");
        Task task2 = new Task(LocalDate.now().plusDays(2), 5, Category.HOBBY, "crocheting");
        toDoListService.addTaskToDoList(task1);
        toDoListService.addTaskToDoList(task2);
        //when
        List<Task> result = toDoListService.getTaskSortedByDate();
        //then
        assertThat(result.get(0).getTerm().isBefore(result.get(1).getTerm())).isTrue();
    }

    @Test
    public void testIfGetEmptyListIfTrySortedEmptyToDoListByDate() {
        //given
        toDoListService.tearDownToDoList();
        //when
        List<Task> result = toDoListService.getTaskSortedByDate();
        //then
        assertTrue(result.isEmpty());
    }

    //5
    @Test
    public void testIfGetTaskByChooseCategory() {
        //given
        Task task1 = new Task(LocalDate.now().plusDays(1), 5, Category.WORK, "meeting");
        Task task2 = new Task(LocalDate.now(), 2, Category.HOBBY, "crocheting");
        Task task3 = new Task(LocalDate.now().plusDays(1), 5, Category.WORK, "coffee");
        toDoListService.addTaskToDoList(task1);
        toDoListService.addTaskToDoList(task2);
        toDoListService.addTaskToDoList(task3);
        //when
        List<Task> result = toDoListService.getTaskByChooseCategory(Category.WORK);
        //then
        assertEquals(2, result.size());
    }

    @Test
    public void testIfGetEmptyListIfCategoryTaskIsNotOnToDoList() {
        //given
        Task task1 = new Task(LocalDate.now().plusDays(1), 5, Category.WORK, "meeting");
        Task task2 = new Task(LocalDate.now(), 2, Category.HOBBY, "crocheting");
        Task task3 = new Task(LocalDate.now().plusDays(1), 5, Category.WORK, "coffee");
        toDoListService.addTaskToDoList(task1);
        toDoListService.addTaskToDoList(task2);
        toDoListService.addTaskToDoList(task3);
        //when
        List<Task> result = toDoListService.getTaskByChooseCategory(Category.HOUSE);
        //then
        assertThat(0).isEqualTo(result.size());
    }

    //6
    @Test
    public void testIfGetTaskByDescription() {
        //given
        Task task1 = new Task(LocalDate.now().plusDays(1), 5, Category.WORK, "meeting and coffee");
        Task task2 = new Task(LocalDate.now(), 2, Category.HOBBY, "crocheting");
        Task task3 = new Task(LocalDate.now().plusDays(1), 5, Category.WORK, "coffee");
        toDoListService.addTaskToDoList(task1);
        toDoListService.addTaskToDoList(task2);
        toDoListService.addTaskToDoList(task3);
        Description description = new Description();
        description.setDescription("coffee");
        //when
        List<Task> result = toDoListService.getTaskByDescription(description);
        //then
        assertEquals(2, result.size());
    }

    @Test
    public void testIfGetTaskByDescriptionIfThatDescriptionNotOnToDoList() {
        //given
        Task task1 = new Task(LocalDate.now().plusDays(1), 5, Category.WORK, "meeting and coffee");
        Task task2 = new Task(LocalDate.now(), 2, Category.HOBBY, "crocheting");
        Task task3 = new Task(LocalDate.now().plusDays(1), 5, Category.WORK, "coffee");
        toDoListService.addTaskToDoList(task1);
        toDoListService.addTaskToDoList(task2);
        toDoListService.addTaskToDoList(task3);
        Description description = new Description();
        description.setDescription("run");
        //when
        List<Task> result = toDoListService.getTaskByDescription(description);
        //then
        assertThat(result.size()).isEqualTo(0);
    }

    //7
    @Test
    public void testIfGetMostUrgenTask() {
        //given
        Task task1 = new Task(LocalDate.now().plusDays(1), 4, Category.WORK, "meeting and coffee");
        Task task2 = new Task(LocalDate.now(), 2, Category.HOBBY, "crocheting");
        Task task3 = new Task(LocalDate.now().plusDays(1), 5, Category.WORK, "coffee");
        toDoListService.addTaskToDoList(task1);
        toDoListService.addTaskToDoList(task2);
        toDoListService.addTaskToDoList(task3);
        //when
        List<Task> result = toDoListService.getMostUrgentTask();
        //then
        assertThat(result.size() == 1).isTrue();
        assertThat(result.get(0)).isEqualTo(task2);
    }

    @Test
    public void testIfGetExceptionIfToDoListIsEmpty() {
        //given
        toDoListService.tearDownToDoList();
        //when
        toDoListService.getMostUrgentTask();
        //then
        assertThatThrownBy(() -> {
            throw new Exception("Empty ToDoList");
        })
                .isInstanceOf(Exception.class)
                .hasMessage("Empty ToDoList");
    }

    //8
    @Test
    public void testIfGetTaskSortedByCategory() {
        //given
        Task task1 = new Task(LocalDate.now().plusDays(1), 4, Category.WORK, "meeting and coffee");
        Task task2 = new Task(LocalDate.now(), 2, Category.HOBBY, "crocheting");
        Task task3 = new Task(LocalDate.now().plusDays(1), 5, Category.WORK, "coffee");
        toDoListService.addTaskToDoList(task1);
        toDoListService.addTaskToDoList(task2);
        toDoListService.addTaskToDoList(task3);
        //when
        Map<Category, List<Task>> result = toDoListService.getTaskSortedByCategory();
        //then
        assertThat(result.get(Category.WORK).size()).isEqualTo(2);
        assertThat(result.get(Category.HOBBY).size()).isEqualTo(1);
        assertThat(result.get(Category.FRIENDS).size()).isEqualTo(0);
    }

    @DisplayName("Check if result map will have empty list for each category-key when toDoList is empty")
    @ParameterizedTest
    @EnumSource(value = Category.class, names = {"FAMILY", "FRIENDS", "HOBBY", "HOUSE", "WORK"})
    public void testIfGetEmptyListInResultMapIfToDoListIsEmpty(Category category) {
        //given
        toDoListService.tearDownToDoList();
        //when
        Map<Category, List<Task>> result = toDoListService.getTaskSortedByCategory();
        //then
        assertThat(result.get(category).size()).isEqualTo(0);
    }

    //9
    @Test
    public void testIfGetTaskByPriority() {
        //given
        Task task1 = new Task(LocalDate.now().plusDays(1), 4, Category.WORK, "meeting and coffee");
        Task task2 = new Task(LocalDate.now(), 2, Category.HOBBY, "crocheting");
        Task task3 = new Task(LocalDate.now().plusDays(1), 4, Category.WORK, "coffee");
        toDoListService.addTaskToDoList(task1);
        toDoListService.addTaskToDoList(task2);
        toDoListService.addTaskToDoList(task3);
        //when
        Map<Integer, List<Task>> result = toDoListService.getTaskByPriority();
        //then
        assertThat(result.get(4).size()).isEqualTo(2);
        assertThat(result.get(2).size()).isEqualTo(1);
        assertThat(result.get(5).size()).isEqualTo(0);
    }

    @DisplayName("Check if result map will have empty list for each priority-key when toDoList is empty")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testIfGetEmptyListInResultMapIfToDoListIsEmpty(int priority) {
        //given
        toDoListService.tearDownToDoList();
        //when
        Map<Integer, List<Task>> result = toDoListService.getTaskByPriority();
        //then
        assertThat(result.get(priority).size()).isEqualTo(0);
    }

    //10
    @Test
    public void testIfGetHighestPriorityForCategory() {
        //given
        Task task1 = new Task(LocalDate.now().plusDays(1), 5, Category.WORK, "meeting and coffee");
        Task task2 = new Task(LocalDate.now(), 3, Category.HOBBY, "crocheting");
        Task task3 = new Task(LocalDate.now().plusDays(1), 5, Category.WORK, "coffee");
        toDoListService.addTaskToDoList(task1);
        toDoListService.addTaskToDoList(task2);
        toDoListService.addTaskToDoList(task3);
        //when
        Map<Category, Optional<Task>> result = toDoListService.getHighestPriorityForCategory();
        //then
        assertThat(result.get(Category.WORK).isPresent()).isTrue();
        assertThat(result.get(Category.HOBBY).isPresent()).isFalse();
    }

    @DisplayName("Check if result map will have empty optional for each category-key when toDoList is empty")
    @ParameterizedTest
    @EnumSource(value = Category.class, names = {"FAMILY", "FRIENDS", "HOBBY", "HOUSE", "WORK"})
    public void testIfGetEmptyListInResultMapWithOptionalIfToDoListIsEmpty(Category category) {
        //given
        toDoListService.tearDownToDoList();
        //when
        Map<Category, Optional<Task>> result = toDoListService.getHighestPriorityForCategory();
        //then
        assertThat(result.get(category).isEmpty()).isTrue();
    }

}
