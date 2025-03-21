package org.example.app.console;


public class ConsoleApp {
    private final ConsoleParser consoleParser;

    public ConsoleApp(ConsoleParser consoleParser) {
        this.consoleParser = consoleParser;
    }

    public void run() {
        consoleParser.start();
    }
}