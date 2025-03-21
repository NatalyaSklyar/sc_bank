package org.example.domain.services;

import org.example.domain.repositories.BankAccountRepository;
import org.example.domain.repositories.CategoryRepository;
import org.example.domain.repositories.OperationRepository;
import org.example.domain.services.importers.AbstractImporter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.UUID;

public class ImporterService {
    BankAccountRepository accountRepository;
    CategoryRepository categoryRepository;
    OperationRepository operationRepository;

    public ImporterService(BankAccountRepository accountRepository, CategoryRepository categoryRepository, OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
        this.operationRepository = operationRepository;
    }

    public void importData(AbstractImporter importer, Path path) throws IOException {
        importer.load(path);

        HashMap<UUID, UUID> accountsMap = new HashMap<>();
        importer.getBankAccounts().forEach((id, account) -> {
            this.accountRepository.save(account);
            accountsMap.put(id, account.getId());
        });

        HashMap<UUID, UUID> categoryMap = new HashMap<>();
        importer.getCategories().forEach((id, category) -> {
            this.categoryRepository.save(category);
            categoryMap.put(id, category.getId());
        });

        importer.getOperations().forEach((id, operation) -> {
            operation.setCategory_id(categoryMap.get(operation.getCategory_id()));
            operation.setBank_account_id(accountsMap.get(operation.getBank_account_id()));
            this.operationRepository.save(operation);
        });
    }
}
