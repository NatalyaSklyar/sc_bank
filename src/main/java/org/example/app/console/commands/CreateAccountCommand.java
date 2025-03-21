package org.example.app.console.commands;

import org.example.domain.services.BankAccountService;

import java.util.Scanner;
import java.util.UUID;

public class CreateAccountCommand implements Command {
    private final BankAccountService bankAccountService;
    private final Scanner scanner;

    public CreateAccountCommand(BankAccountService bankAccountService, Scanner scanner) {
        this.bankAccountService = bankAccountService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Введите название счета: ");
        String accountName = scanner.nextLine();
        System.out.print("Введите баланс: ");
        double balance = Double.parseDouble(scanner.nextLine());
        UUID accountId = bankAccountService.createAccount(accountName, balance).getId();
        System.out.println("Счет создан с ID: " + accountId);
    }
}
