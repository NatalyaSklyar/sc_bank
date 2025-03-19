package org.example.app;

import org.example.domain.repository_service.BankAccountService;
import org.example.domain.repository_service.CategoryService;
import org.example.domain.repository_service.OperationService;

import java.util.Scanner;
import java.util.UUID;


public class ConsoleParser {
    private final BankAccountService bankAccountService;
    private final CategoryService categoryService;
    private final OperationService operationService;

    public ConsoleParser(BankAccountService bankAccountService, CategoryService categoryService, OperationService operationService) {
        this.bankAccountService = bankAccountService;
        this.categoryService = categoryService;
        this.operationService = operationService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Добро пожаловать в систему учета финансов! Введите команду:");
        while (true) {
            System.out.print("> ");
            command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("exit")) {
                System.out.println("Выход из программы.");
                break;
            } else if (command.startsWith("create account")) {
                System.out.print("Введите название счета: ");
                String name = scanner.nextLine();
                System.out.print("Введите баланс: ");
                double balance = Double.parseDouble(scanner.nextLine());
                UUID id = bankAccountService.createAccount(name, balance).getId();
                System.out.println("Счет создан с ID: " + id);
            } else if (command.startsWith("show accounts")) {
                bankAccountService.getAllAccounts().forEach(System.out::println);
            } else if (command.startsWith("create category")) {
                System.out.print("Введите тип (income/expense): ");
                String type = scanner.nextLine();
                System.out.print("Введите название категории: ");
                String name = scanner.nextLine();
                UUID id = categoryService.createCategory(type, name).getId();
                System.out.println("Категория создана с ID: " + id);
            } else if (command.startsWith("show categories")) {
                categoryService.getAllCategories().forEach(System.out::println);
            } else if (command.startsWith("create operation")) {
                System.out.print("Введите тип операции (income/expense): ");
                String type = scanner.nextLine();
                System.out.print("Введите ID счета: ");
                UUID accountId = UUID.fromString(scanner.nextLine());
                System.out.print("Введите сумму: ");
                float amount = Float.parseFloat(scanner.nextLine());
                System.out.print("Введите ID категории: ");
                UUID categoryId = UUID.fromString(scanner.nextLine());
                System.out.print("Введите описание (опционально): ");
                String description = scanner.nextLine();
                UUID id = operationService.createOperation(type, accountId, amount, description, categoryId).getId();
                System.out.println("Операция создана с ID: " + id);
            } else if (command.startsWith("show operations")) {
                operationService.getAllOperations().forEach(System.out::println);
            } else {
                System.out.println("Неизвестная команда. Доступные команды:");
                System.out.println("  - create account");
                System.out.println("  - show accounts");
                System.out.println("  - create category");
                System.out.println("  - show categories");
                System.out.println("  - create operation");
                System.out.println("  - show operations");
                System.out.println("  - exit");
            }
        }
        scanner.close();
    }
}