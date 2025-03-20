package org.example.domain.model;

import java.util.UUID;

public class BankAccount extends FinanceEntity {
    private String name;
    private double balance;

    public BankAccount(UUID id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public BankAccount() {
        this.id = UUID.randomUUID();
        this.name = "";
        this.balance = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
