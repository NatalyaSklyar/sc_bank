package org.example.domain.repository_service;

import org.example.domain.model.Operation;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalyticsService {
    private final OperationService operationService;

    public AnalyticsService(OperationService operationService) {
        this.operationService = operationService;
    }

    public double calculateNetIncome() {
        List<Operation> operations = operationService.getAllOperations();
        double income = operations.stream()
                .filter(op -> "доход".equals(op.type))
                .mapToDouble(op -> op.amount)
                .sum();
        double expenses = operations.stream()
                .filter(op -> "расход".equals(op.type))
                .mapToDouble(op -> op.amount)
                .sum();
        return income - expenses;
    }

    public Map<String, Double> groupByCategory() {
        List<Operation> operations = operationService.getAllOperations();
        return operations.stream()
                .collect(Collectors.groupingBy(op -> op.category_id + "", Collectors.summingDouble(op -> op.amount)));
    }
}
