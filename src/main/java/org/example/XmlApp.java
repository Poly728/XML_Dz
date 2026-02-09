package org.example;

import org.example.controller.TaskController;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        TaskController taskController = context.getBean("taskController", TaskController.class);

        taskController.displayMenu();

        context.close();
    }
}
