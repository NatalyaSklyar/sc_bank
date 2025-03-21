package org.example.domain.services.exporters;

import org.example.domain.model.BankAccount;
import org.example.domain.model.Category;
import org.example.domain.model.Operation;

import java.nio.file.Path;
import java.util.List;

public interface ExportVisitor {
    void visit(List<BankAccount> accounts, List<Category> categories, List<Operation> operations);
    void finish(Path path) throws Exception; // метод завершения экспорта
}