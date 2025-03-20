package org.example.domain.services;
import org.example.domain.model.BankAccount;
import org.example.domain.model.Category;
import org.example.domain.model.Operation;
import org.example.domain.repositories.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;


public class OperationService {
    private final Repository<Operation> operationRepository;
    private final Repository<BankAccount> accountsRepository;
    private final Repository<Category> categoryRepository;

    public OperationService(
            Repository<Operation> operationRepository,
            Repository<BankAccount> accountsRepository,
            Repository<Category> categoryRepository
            ) {
        this.operationRepository = operationRepository;
        this.accountsRepository = accountsRepository;
        this.categoryRepository = categoryRepository;
    }

    public Operation createOperation(String type, UUID bankAccountId, double amount, String description, UUID categoryId) {
        if (!accountsRepository.contains(bankAccountId)) {
            throw new NoSuchElementException("Bank account does not exist.");
        }
        if (!categoryRepository.contains(categoryId)) {
            throw new NoSuchElementException("Category does not exist.");
        }
        Operation operation = new Operation(UUID.randomUUID(), type, bankAccountId, amount, new java.util.Date(), description, categoryId);
        operationRepository.save(operation);
        return operation;
    }

    public void updateOperation(Operation operation) {
        operationRepository.update(operation);
    }

    public void deleteOperation(UUID id) {
        operationRepository.delete(id);
    }

    public Optional<Operation> getOperationById(UUID id) {
        return operationRepository.findById(id);
    }

    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
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
