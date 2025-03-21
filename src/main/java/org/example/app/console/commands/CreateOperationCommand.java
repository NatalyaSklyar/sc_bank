package org.example.app.console.commands;

import org.example.domain.services.OperationService;

import java.util.Scanner;
import java.util.UUID;

public class CreateOperationCommand implements Command {
    private final OperationService operationService;
    private final Scanner scanner;

    public CreateOperationCommand(OperationService operationService, Scanner scanner) {
        this.operationService = operationService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Введите тип операции (income/expense): ");
        String operationType = scanner.nextLine();
        System.out.print("Введите ID счета: ");
        UUID operationAccountId = UUID.fromString(scanner.nextLine());
        System.out.print("Введите сумму: ");
        float amount = Float.parseFloat(scanner.nextLine());
        System.out.print("Введите ID категории: ");
        UUID operationCategoryId = UUID.fromString(scanner.nextLine());
        System.out.print("Введите описание (опционально): ");
        String description = scanner.nextLine();
        UUID operationId = operationService.createOperation(operationType, operationAccountId, amount, description, operationCategoryId).getId();
        System.out.println("Операция создана с ID: " + operationId);
    }
}
