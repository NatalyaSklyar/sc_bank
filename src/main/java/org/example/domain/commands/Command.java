package org.example.domain.commands;


import org.example.domain.services.BankAccountService;
import org.example.domain.services.CategoryService;
import org.example.domain.services.OperationService;
import java.util.Scanner;
import java.util.UUID;

public interface Command {
    void execute();
}

