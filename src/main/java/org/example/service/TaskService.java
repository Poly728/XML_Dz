package org.example.service;

import org.example.model.Task;
import org.example.repository.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getPendingTasks() {
        return taskRepository.getAll().stream()
                .filter(task -> !task.isCompleted())
                .collect(Collectors.toList());
    }

    public long getCompletedCount() {
        return taskRepository.getAll().stream()
                .filter(Task::isCompleted)
                .count();
    }

    public void addTask(Task task) {
        taskRepository.add(task);
    }

    public void completeTask(int id) {
        taskRepository.markCompleted(id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAll();
    }

    public void deleteTask(int id) {
        taskRepository.delete(id);
    }
}
