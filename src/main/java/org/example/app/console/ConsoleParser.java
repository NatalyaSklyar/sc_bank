package org.example.app.console;

import org.example.app.console.commands.*;
import org.example.app.console.commands.timmedCommand.TimedCommand;
import org.example.domain.services.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleParser {
    private final Map<String, Command> commands = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleParser(BankAccountService bankAccountService,
                         CategoryService categoryService,
                         OperationService operationService,
                         ExporterService exporterService,
                         ImporterService importerService,
                         AnalyticsService analyticsService) {
        commands.put("1", new CreateAccountCommand(bankAccountService, scanner));
        commands.put("2", new ShowAccountsCommand(bankAccountService));
        commands.put("3", new CreateCategoryCommand(categoryService, scanner));
        commands.put("4", new ShowCategoriesCommand(categoryService));
        commands.put("5", new CreateOperationCommand(operationService, scanner));
        commands.put("6", new ShowOperationsCommand(operationService));
        commands.put("7", new ExportCommand(exporterService, scanner));
        commands.put("8", new ImportCommand(importerService, scanner));
        commands.put("9", new GroupByCategoryCommand(analyticsService));
        commands.put("10", new CalculateNetIncomeCommand(analyticsService));
    }

    public void start() {
        System.out.println("Добро пожаловать в систему учета финансов!");
        while (true) {
            System.out.println("Выберите команду:");
            System.out.println("  1 - Создать счет");
            System.out.println("  2 - Показать счета");
            System.out.println("  3 - Создать категорию");
            System.out.println("  4 - Показать категории");
            System.out.println("  5 - Создать операцию");
            System.out.println("  6 - Показать операции");
            System.out.println("  7 - Экспорт в файл");
            System.out.println("  8 - Импорт из файла");
            System.out.println("  9 - Сгруппировать расходы по категориям");
            System.out.println("  10 - Вычислить общий доход");
            System.out.println("  0 - Выйти");
            System.out.print("> ");

            String command = scanner.nextLine().trim();
            if (command.equals("0")) {
                System.out.println("Выход из программы.");
                scanner.close();
                return;
            }

            Command action = commands.get(command);
            if (action != null) {
                try {
                    new TimedCommand(action, action.getClass().getSimpleName()).execute();
                } catch (Exception e) {
                    System.out.println("Ошибка при вызове команды: " + e);
                }
            } else {
                System.out.println("Некорректный ввод. Пожалуйста, выберите номер команды из списка.");
            }
        }
    }
}
