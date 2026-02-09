package org.example.controller;

import org.example.model.Task;
import org.example.service.TaskService;

import java.util.List;
import java.util.Scanner;

public class TaskController {
    private final TaskService taskService;
    private Scanner scanner;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    public void initScanner() {
        this.scanner = new Scanner(System.in);
    }

    public void listPendingTasks() {
        List<Task> pendingTasks = taskService.getPendingTasks();
        System.out.println("\n=== Невыполненные задачи ===");
        if (pendingTasks.isEmpty()) {
            System.out.println("Нет невыполненных задач!");
        } else {
            for (Task task : pendingTasks) {
                System.out.println("ID: " + task.getId() +
                                 " | Название: " + task.getTitle() +
                                 " | Создано: " + task.getCreatedAt());
            }
        }
        System.out.println("\nВсего завершённых задач: " + taskService.getCompletedCount());
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n=== Управление задачами ===");
            System.out.println("1. Показать невыполненные задачи");
            System.out.println("2. Показать все задачи");
            System.out.println("3. Добавить задачу");
            System.out.println("4. Отметить задачу как выполненную");
            System.out.println("5. Удалить задачу");
            System.out.println("6. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    listPendingTasks();
                    break;
                case 2:
                    listAllTasks();
                    break;
                case 3:
                    addNewTask();
                    break;
                case 4:
                    markTaskCompleted();
                    break;
                case 5:
                    deleteTask();
                    break;
                case 6:
                    System.out.println("До свидания!");
                return;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }

    private void listAllTasks() {
        List<Task> allTasks = taskService.getAllTasks();
        System.out.println("\n=== Все задачи ===");
        if (allTasks.isEmpty()) {
            System.out.println("Нет задач!");
        } else {
            for (Task task : allTasks) {
                String status = task.isCompleted() ? "✓" : "✗";
                System.out.println("ID: " + task.getId() +
                                 " | Название: " + task.getTitle() +
                                 " | Статус: " + status +
                                 " | Создано: " + task.getCreatedAt());
            }
        }
    }

    private void addNewTask() {
        System.out.print("Введите название задачи: ");
        String title = scanner.nextLine();

        List<Task> allTasks = taskService.getAllTasks();
        int newId = allTasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0) + 1;

        Task newTask = new Task(newId, title, false, java.time.LocalDateTime.now());
        taskService.addTask(newTask);
        System.out.println("Задача добавлена!");
    }

    private void markTaskCompleted() {
        System.out.print("Введите ID задачи для завершения: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        taskService.completeTask(id);
        System.out.println("Задача отмечена как выполненная!");
    }

    private void deleteTask() {
        System.out.print("Введите ID задачи для удаления: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        taskService.deleteTask(id);
        System.out.println("Задача удалена!");
    }
}
