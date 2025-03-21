package org.example.app.console.commands;

import org.example.domain.services.AnalyticsService;

public class CalculateNetIncomeCommand implements Command {

    private final AnalyticsService analyticsService;

    public CalculateNetIncomeCommand(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @Override
    public void execute() {
        double netIncome = analyticsService.calculateNetIncome();
        System.out.printf("Чистый доход: %.2f\n", netIncome);
    }
}