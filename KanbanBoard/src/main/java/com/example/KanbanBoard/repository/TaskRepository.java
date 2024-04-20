package com.example.KanbanBoard.repository;

import com.example.KanbanBoard.data.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TaskRepository {
    private final List<Task> tasks;

}
