package org.example.app;

import org.example.app.console.ConsoleApp;
import org.example.infrastructure.di.AppConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        // Загружаем Spring-контейнер
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            ConsoleApp consoleApp = context.getBean(ConsoleApp.class);
            consoleApp.run();
        }
    }
}