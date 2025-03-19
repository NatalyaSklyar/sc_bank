package org.example.domain.repository_service;
import org.example.domain.model.Operation;
import org.example.domain.repository_service.Repository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class OperationService {
    private final Repository<Operation> repository;

    public OperationService(Repository<Operation> repository) {
        this.repository = repository;
    }

    public Operation createOperation(String type, UUID bankAccountId, float amount, String description, UUID categoryId) {
        Operation operation = new Operation(UUID.randomUUID(), type, bankAccountId, amount, new java.util.Date(), description, categoryId);
        repository.save(operation);
        return operation;
    }

    public void updateOperation(Operation operation) {
        repository.update(operation);
    }

    public void deleteOperation(UUID id) {
        repository.delete(id);
    }

    public Optional<Operation> getOperationById(UUID id) {
        return repository.findById(id);
    }

    public List<Operation> getAllOperations() {
        return repository.findAll();
    }

//    public void showAllOperations() {
//        List<Operation> operations = getAllOperations();
//        for (Operation operation : operations) {
//            System.out.println("Type: " + operation.getType());
//            System.out.println("Bank Account ID: " + operation.getBankAccountId());
//            System.out.println("Amount: " + operation.getAmount());
//            System.out.println("Date: " + operation.getDate());
//            System.out.println("Description: " + operation.getDescription());
//            System.out.println("Category ID: " + operation.getCategoryId());
//            System.out.println("-------------------------");
//        }
//    }
}
