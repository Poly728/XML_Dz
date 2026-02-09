package org.example.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Setter;
import org.example.model.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryJSON implements TaskRepository {
    @Setter
    private String filePath;
    private final ObjectMapper objectMapper;

    public TaskRepositoryJSON() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public List<Task> getAll() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<Task>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void add(Task task) {
        List<Task> tasks = getAll();
        tasks.add(task);
        saveTasks(tasks);
    }

    @Override
    public void markCompleted(int id) {
        List<Task> tasks = getAll();
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setCompleted(true);
                break;
            }
        }
        saveTasks(tasks);
    }

    @Override
    public void delete(int id) {
        List<Task> tasks = getAll();
        tasks.removeIf(task -> task.getId() == id);
        saveTasks(tasks);
    }

    private void saveTasks(List<Task> tasks) {
        try {
            File file = new File(filePath);
            objectMapper.writeValue(file, tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
