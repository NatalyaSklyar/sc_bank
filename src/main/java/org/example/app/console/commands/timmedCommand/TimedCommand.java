package org.example.app.console.commands.timmedCommand;

import org.example.app.console.commands.Command;

public class TimedCommand implements Command {
    private final Command wrappedCommand;
    private final String commandName;

    public TimedCommand(Command wrappedCommand, String commandName) {
        this.wrappedCommand = wrappedCommand;
        this.commandName = commandName;
    }

    @Override
    public void execute() throws Exception {
        long start = System.nanoTime();
        wrappedCommand.execute();
        long end = System.nanoTime();
        System.out.printf("Команда '%s' выполнена за %.2f мс.%n", commandName, (end - start) / 1_000_000.0);
    }
}
