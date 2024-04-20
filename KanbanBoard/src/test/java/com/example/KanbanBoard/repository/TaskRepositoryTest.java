package com.example.KanbanBoard.repository;

import com.example.KanbanBoard.data.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
public class TaskRepositoryTest {
    private TaskRepository taskRepository;
    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        taskRepository = new TaskRepository();

        ArrayList<String> labels1 = new ArrayList<>();
        labels1.add("label1");
        labels1.add("label2");
        task1 = new Task("Task 1", "Description 1", 1, labels1, "To Do", 60);

        ArrayList<String> labels2 = new ArrayList<>();
        labels2.add("label3");
        labels2.add("label4");
        task2 = new Task("Task 2", "Description 2", 2, labels2, "In Progress", 120);
    }

    @Test
    public void testAddTask() {
        taskRepository.addTask(task1);
        Assertions.assertEquals(1, taskRepository.getTasks().size());
        Assertions.assertEquals(task1, taskRepository.getTasks().get(0));
    }

    @Test
    public void testUpdateTask() {
        taskRepository.addTask(task1);
        Task updatedTask = new Task("Updated Task", "Updated Description", 1, task1.getLabels(), "Done", 30);
        taskRepository.updateTask(updatedTask);
        Assertions.assertEquals(1, taskRepository.getTasks().size());
        Assertions.assertEquals(updatedTask, taskRepository.getTasks().get(0));
    }

    @Test
    public void testDeleteTask() {
        taskRepository.addTask(task1);
        taskRepository.addTask(task2);
        taskRepository.deleteTask(task1.getTaskID());
        Assertions.assertEquals(1, taskRepository.getTasks().size());
        Assertions.assertEquals(task2, taskRepository.getTasks().get(0));
    }
}
