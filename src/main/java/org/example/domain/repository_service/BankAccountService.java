package org.example.domain.repository_service;

import org.example.domain.model.BankAccount;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;


public class BankAccountService{
    private final BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository) {
        this.repository = repository;
    }

    public BankAccount createAccount(String name, double initialBalance) {
        BankAccount account = new BankAccount(UUID.randomUUID(), name, initialBalance);
        repository.save(account);
        return account;
    }

    public void deleteAccount(UUID id) {
        repository.delete(id);
    }

    public BankAccount getAccount(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bank account not found"));
    }

    public void deposit(UUID id, double amount) {
        BankAccount account = getAccount(id);
        account.deposit(amount);
        repository.update(account);
    }

    public void withdraw(UUID id, double amount) {
        BankAccount account = getAccount(id);
        account.withdraw(amount);
        repository.update(account);
    }

    public List<BankAccount> getAllAccounts() {
        return repository.findAll();
    }
}