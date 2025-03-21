package org.example.domain.services.exporters;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.example.domain.model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvExporter implements Exporter {

    @Override
    public ExportVisitor createVisitor() {
        return new ExportVisitor() {
            List<BankAccount> accounts;
            List<Category> categories;
            List<Operation> operations;

            @Override
            public void visit(List<BankAccount> accounts, List<Category> categories, List<Operation> operations) {
                this.accounts = accounts;
                this.categories = categories;
                this.operations = operations;
            }

            @Override
            public void finish(Path directoryPath) throws IOException {
                exportAccounts(directoryPath + "/accounts.csv");
                exportCategories(directoryPath + "/categories.csv");
                exportOperations(directoryPath + "/operations.csv");
            }

            @SuppressWarnings("deprecation")
            private void exportAccounts(String filePath) throws IOException {
                try (FileWriter out = new FileWriter(filePath);
                     CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                             .withHeader("id", "name", "balance"))) {
                    for (BankAccount acc : accounts) {
                        printer.printRecord(
                                acc.getId(),
                                acc.getName(),
                                acc.getBalance()
                        );
                    }
                }
            }

            @SuppressWarnings("deprecation")
            private void exportCategories(String filePath) throws IOException {
                try (FileWriter out = new FileWriter(filePath);
                     CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                             .withHeader("id", "name"))) {
                    for (Category cat : categories) {
                        printer.printRecord(
                                cat.getId(),
                                cat.getName()
                        );
                    }
                }
            }

            @SuppressWarnings("deprecation")
            private void exportOperations(String filePath) throws IOException {
                try (FileWriter out = new FileWriter(filePath);
                     CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                             .withHeader("id", "amount", "date", "bank_account_id", "category_id"))) {
                    for (Operation op : operations) {
                        printer.printRecord(
                                op.getId(),
                                op.getAmount(),
                                op.getDate(),
                                op.getBank_account_id(),
                                op.getCategory_id()
                        );
                    }
                }
            }
        };
    }
}