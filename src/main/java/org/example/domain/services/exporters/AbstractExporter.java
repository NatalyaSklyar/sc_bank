package org.example.domain.services.exporters;

import org.example.domain.model.BankAccount;
import org.example.domain.model.Category;
import org.example.domain.model.Operation;
import org.example.domain.repositories.CategoryRepository;

import java.util.List;

public abstract class AbstractExporter {
    public abstract void export(
            List<BankAccount> accountList,
            List<Category> categoryList,
            List<Operation> operationList
    );
}
