package org.example.app.console.commands;

import org.example.domain.services.BankAccountService;

public class ShowAccountsCommand implements Command {
    private final BankAccountService bankAccountService;

    public ShowAccountsCommand(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @Override
    public void execute() {
        bankAccountService.getAllAccounts().forEach(System.out::println);
    }
}
