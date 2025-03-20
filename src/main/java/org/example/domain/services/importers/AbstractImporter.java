package org.example.domain.services.importers;

import org.example.domain.model.BankAccount;
import org.example.domain.model.Category;
import org.example.domain.model.Operation;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;


public abstract class AbstractImporter {

    public abstract HashMap<UUID, BankAccount> getBankAccounts();
    public abstract HashMap<UUID, Operation> getOperations();
    public abstract HashMap<UUID, Category> getCategories();

    public abstract void load() throws IOException;
}