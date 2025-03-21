package org.example.domain.services;

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
                .filter(op -> "income".equals(op.getType()))
                .mapToDouble(Operation::getAmount)
                .sum();
        double expenses = operations.stream()
                .filter(op -> "expense".equals(op.getType()))
                .mapToDouble(Operation::getAmount)
                .sum();
        return income - expenses;
    }

    public Map<String, Double> groupByCategory() {
        List<Operation> operations = operationService.getAllOperations();
        return operations.stream()
                .collect(Collectors.groupingBy(op -> op.getCategory_id() + "", Collectors.summingDouble(Operation::getAmount)));
    }
}
