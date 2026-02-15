package org.example;

import org.example.config.SpringConfig;
import org.example.controller.TaskController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class XmlApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);

        TaskController taskController = context.getBean("taskController", TaskController.class);
        taskController.displayMenu();

        context.close();
    }
}
