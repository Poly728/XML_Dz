package org.example.config;

import org.example.controller.TaskController;
import org.example.repository.TaskRepository;
import org.example.repository.TaskRepositoryJSON;
import org.example.service.TaskService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "java",
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Component.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = TaskRepository.class),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\..+Repository")
        })

public class SpringConfig {
    @Bean("taskController")
    public TaskController taskController(TaskService taskService) {
        return new TaskController(taskService);
    }

    @Bean("taskService")
    public TaskService taskService(TaskRepository taskRepository) {
        return new TaskService(taskRepository);
    }

    @Bean("taskRepository")
    public TaskRepository taskRepository() {
        return new TaskRepositoryJSON();
    }

}
