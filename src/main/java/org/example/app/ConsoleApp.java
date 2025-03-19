package org.example.app;

import org.springframework.stereotype.Component;

@Component
public class ConsoleApp {
    private final ConsoleParser consoleParser;

    public ConsoleApp(ConsoleParser consoleParser) {
        this.consoleParser = consoleParser;
    }

    public void run() {
        consoleParser.start();
    }
}