package org.example.domain.services.importers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.example.domain.model.BankAccount;
import org.example.domain.model.Category;
import org.example.domain.model.Operation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CsvImporter extends AbstractImporter {
    private final HashMap<UUID, BankAccount> bankAccounts = new HashMap<>();
    private final HashMap<UUID, Operation> operations = new HashMap<>();
    private final HashMap<UUID, Category> categories = new HashMap<>();

    private Path accountsPath;
    private Path operationsPath;
    private Path categoriesPath;


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

    private CSVParser openCSV(Path filePath) throws IOException {
        if (!Files.exists(filePath) || Files.size(filePath) == 0) {
            throw new FileNotFoundException();
        }
        Reader reader = new FileReader(filePath.toString());
        return new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
    }

    @Override
    public void load(Path path) throws IOException {
        CSVParser accountsParser = openCSV(Path.of(path + "/accounts.csv"));
        CSVParser categoriesParser = openCSV(Path.of(path + "/categories.csv"));
        CSVParser operationsParser = openCSV(Path.of(path + "/operations.csv"));

        List<BankAccount> bankAccountsList = accountsParser.stream().map(
                record -> new BankAccount(
                        UUID.fromString(record.get("id")),
                        record.get("name"),
                        Double.parseDouble(record.get("balance"))
                )
        ).toList();

        List<Category> categoriesList = categoriesParser.stream().map(
                        record -> new Category(
                                UUID.fromString(record.get("id")),
                                record.get("type"),
                                record.get("name")
                        )
                )
                .toList();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Operation> operationsList = operationsParser.stream().map(
                        record -> {
                            try {
                                return new Operation(
                                        UUID.fromString(record.get("id")),
                                        record.get("type"),
                                        UUID.fromString(record.get("bank_account_id")),
                                        Double.parseDouble(record.get("amount")),
                                        sdf.parse(record.get("date")),
                                        record.get("description"),
                                        UUID.fromString(record.get("category_id"))
                                );
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                .toList();


        bankAccountsList.forEach(acc -> bankAccounts.put(acc.getId(), acc));
        categoriesList.forEach(cat -> categories.put(cat.getId(), cat));
        operationsList.forEach(op -> operations.put(op.getId(), op));
    }
}