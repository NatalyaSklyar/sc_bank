package org.example.domain.services.exporters;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.example.domain.model.BankAccount;
import org.example.domain.model.Category;
import org.example.domain.model.Operation;

import java.nio.file.Path;
import java.util.List;

class JsonContainer {
    List<BankAccount> accountList;
    List<Category> categoryList;
    List<Operation> operationList;
}

//public class JsonExporter extends AbstractExporter {
//    @Override
//    public void export(List<BankAccount> accountList, List<Category> categoryList, List<Operation> operationList, Path filePath) {
////        String
//    }
//
//}
