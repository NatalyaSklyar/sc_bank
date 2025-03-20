package org.example.domain.services;

import org.example.domain.repositories.BankAccountRepository;
import org.example.domain.repositories.CategoryRepository;
import org.example.domain.repositories.OperationRepository;
import org.example.domain.services.importers.AbstractImporter;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class ImporterService {
    BankAccountRepository accountRepository;
    CategoryRepository categoryRepository;
    OperationRepository operationRepository;

    public ImporterService(BankAccountRepository accountRepository,
                           CategoryRepository categoryRepository,
                           OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
        this.operationRepository = operationRepository;
    }

    public void importData(AbstractImporter importer) throws IOException {
        importer.load();

        HashMap<UUID, UUID> accountsMap = new HashMap<>();
        importer.getBankAccounts().forEach(
                (id, account) -> {
                    this.accountRepository.save(account);
                    accountsMap.put(id, account.getId());
                }
        );

        HashMap<UUID, UUID> categoryMap = new HashMap<>();
        importer.getCategories().forEach(
                (id, category) -> {
                    this.categoryRepository.save(category);
                    categoryMap.put(id, category.getId());
                }
        );

        importer.getOperations().forEach(
                (id, operation) -> {
                    operation.category_id = categoryMap.get(operation.category_id);
                    operation.bank_account_id = accountsMap.get(operation.bank_account_id);
                    this.operationRepository.save(operation);
                }
        );
    }
}
