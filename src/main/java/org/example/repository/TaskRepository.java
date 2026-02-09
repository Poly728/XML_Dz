package org.example.repository;

import org.example.model.Task;

import java.util.List;

public interface TaskRepository {
    List<Task> getAll();
    void add(Task task);
    void markCompleted(int id);
    void delete(int id);
}
