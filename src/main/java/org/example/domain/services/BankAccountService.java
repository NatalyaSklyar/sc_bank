package org.example.domain.services;

import org.example.domain.model.BankAccount;
import org.example.domain.repositories.BankAccountRepository;

import java.util.NoSuchElementException;
import java.util.UUID;


public class BankAccountService {
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
        if (amount > 0) {
            account.setBalance(account.getBalance() + amount);
        } else {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
    }

    public void withdraw(UUID id, double amount) {
        BankAccount account = getAccount(id);
        if (amount > 0) {
            account.setBalance(account.getBalance() - amount);
        } else {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
    }

    public double getBalance(UUID id) {
        return repository.findById(id).orElseThrow().getBalance();
    }

    public boolean accountExists(UUID id) {
        return repository.findById(id).isPresent();
    }

    public Iterable<BankAccount> getAllAccounts() {
        return repository.findAll();
    }
}