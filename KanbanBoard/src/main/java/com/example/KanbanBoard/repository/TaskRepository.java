package com.example.KanbanBoard.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.KanbanBoard.data.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@AllArgsConstructor
public class TaskRepository {

    private List<Task> tasks;
    public TaskRepository() {
        this.tasks = new ArrayList<>();
    }
    public void addTask(Task task) {
        tasks.add(task);
    }
    public void updateTask(Task updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskID() == updatedTask.getTaskID()) {
                tasks.set(i, updatedTask);
                return;
            }
        }
        System.out.println("Task with ID " + updatedTask.getTaskID() + " not found.");
    }
    public void deleteTask(int taskID) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskID() == taskID) {
                tasks.remove(i);
                return;
            }
        }
        System.out.println("Task with ID " + taskID + " not found.");
    }
    public List<Task> getTasks() {
        return tasks;
    }
}


