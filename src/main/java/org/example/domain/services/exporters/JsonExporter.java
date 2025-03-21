package org.example.domain.services.exporters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.domain.model.*;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonExporter implements Exporter {
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
            public void finish(Path path) throws Exception {
                ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
                var data = new ExportData(accounts, categories, operations);
                mapper.writeValue(new File(path.toString()), data);
            }

            record ExportData(List<BankAccount> accounts,
                              List<Category> categories,
                              List<Operation> operations) {}
        };
    }
}