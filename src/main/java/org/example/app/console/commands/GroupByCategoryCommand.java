package org.example.app.console.commands;

import org.example.domain.services.AnalyticsService;

import java.util.Map;

public class GroupByCategoryCommand implements Command {

    private final AnalyticsService analyticsService;

    public GroupByCategoryCommand(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @Override
    public void execute() {
        Map<String, Double> grouped = analyticsService.groupByCategory();
        System.out.println("Суммы операций по категориям:");
        grouped.forEach((categoryId, total) -> {
            System.out.printf("Категория %s: %.2f\n", categoryId, total);
        });
    }
}