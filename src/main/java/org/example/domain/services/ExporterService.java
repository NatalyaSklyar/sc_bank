package org.example.domain.services;

import org.example.domain.repositories.*;
import org.example.domain.services.exporters.Exporter;
import org.example.domain.services.exporters.ExportVisitor;

import java.nio.file.Path;

public class ExporterService {
    BankAccountRepository accountRepository;
    CategoryRepository categoryRepository;
    OperationRepository operationRepository;

    public ExporterService(BankAccountRepository accountRepository, CategoryRepository categoryRepository, OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
        this.operationRepository = operationRepository;
    }

    public void exportData(Exporter exporter, Path path) throws Exception {
        ExportVisitor visitor = exporter.createVisitor();

        visitor.visit(accountRepository.findAll(), categoryRepository.findAll(), operationRepository.findAll());

        visitor.finish(path);
    }
}