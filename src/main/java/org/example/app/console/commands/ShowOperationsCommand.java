package org.example.app.console.commands;

import org.example.domain.services.OperationService;

public class ShowOperationsCommand implements Command {
    private final OperationService operationService;

    public ShowOperationsCommand(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public void execute() {
        operationService.getAllOperations().forEach(System.out::println);
    }
}
