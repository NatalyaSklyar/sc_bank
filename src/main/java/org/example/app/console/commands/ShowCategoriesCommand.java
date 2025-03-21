package org.example.app.console.commands;

import org.example.domain.services.CategoryService;

public class ShowCategoriesCommand implements Command {
    private final CategoryService categoryService;

    public ShowCategoriesCommand(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void execute() {
        categoryService.getAllCategories().forEach(System.out::println);
    }
}
