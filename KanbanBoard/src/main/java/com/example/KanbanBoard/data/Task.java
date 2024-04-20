package com.example.KanbanBoard.data;

import java.util.ArrayList;

public class Task {
    private String title;
    private String description;
    private int taskID;
    private ArrayList<String> labels;
    private String status;
    private int estimatedTime;

    public Task(String title, String description, int taskID, ArrayList<String> labels, String status, int estimatedTime) {
        this.title = title;
        this.description = description;
        this.taskID = taskID;
        this.labels = labels;
        this.status = status;
        this.estimatedTime = estimatedTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public ArrayList<String> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<String> labels) {
        this.labels = labels;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }
    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", taskID=" + taskID +
                ", labels=" + labels +
                ", status='" + status + '\'' +
                ", estimatedTime=" + estimatedTime +
                '}';
    }
}
