package org.example.domain.services.importers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.model.BankAccount;
import org.example.domain.model.Category;
import org.example.domain.model.Operation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class JsonImporter extends AbstractImporter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HashMap<UUID, BankAccount> bankAccounts = new HashMap<>();
    private final HashMap<UUID, Operation> operations = new HashMap<>();
    private final HashMap<UUID, Category> categories = new HashMap<>();

    private final Path filePath;

    public JsonImporter(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public HashMap<UUID, BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    @Override
    public HashMap<UUID, Operation> getOperations() {
        return operations;
    }

    @Override
    public HashMap<UUID, Category> getCategories() {
        return categories;
    }

    @Override
    public void load() throws IOException {
        if (!Files.exists(filePath) || Files.size(filePath) == 0) {
            return;
        }

        var rootNode = objectMapper.readTree(filePath.toFile());

        List<BankAccount> bankAccountsList = objectMapper.readValue(
                rootNode.get("bankAccounts").traverse(),
                new TypeReference<List<BankAccount>>() {
                }
        );

        List<Category> categoriesList = objectMapper.readValue(
                rootNode.get("categories").traverse(),
                new TypeReference<List<Category>>() {
                }
        );

        List<Operation> operationsList = objectMapper.readValue(
                rootNode.get("operations").traverse(),
                new TypeReference<List<Operation>>() {
                }
        );

        bankAccountsList.forEach(acc -> bankAccounts.put(acc.getId(), acc));
        categoriesList.forEach(cat -> categories.put(cat.getId(), cat));
        operationsList.forEach(op -> operations.put(op.getId(), op));
    }
}
