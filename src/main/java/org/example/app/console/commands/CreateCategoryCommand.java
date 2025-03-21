package org.example.app.console.commands;

import org.example.domain.services.CategoryService;

import java.util.Scanner;
import java.util.UUID;

public class CreateCategoryCommand implements Command {
    private final CategoryService categoryService;
    private final Scanner scanner;

    public CreateCategoryCommand(CategoryService categoryService, Scanner scanner) {
        this.categoryService = categoryService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Введите тип (income/expense): ");
        String categoryType = scanner.nextLine();
        System.out.print("Введите название категории: ");
        String categoryName = scanner.nextLine();
        UUID categoryId = categoryService.createCategory(categoryType, categoryName).getId();
        System.out.println("Категория создана с ID: " + categoryId);
    }
}
