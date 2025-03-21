package org.example.app.console.commands;

import org.example.domain.services.ImporterService;
import org.example.domain.services.importers.AbstractImporter;
import org.example.domain.services.importers.JsonImporter;
import org.example.domain.services.importers.CsvImporter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ImportCommand implements Command {
    private final ImporterService importerService;
    private final Scanner scanner;

    public ImportCommand(ImporterService importerService, Scanner scanner) {
        this.importerService = importerService;
        this.scanner = scanner;
    }

    @Override
    public void execute() throws IOException {
        System.out.println("Выберите формат импорта (json/csv):");
        String format = scanner.nextLine().trim().toLowerCase();

        AbstractImporter importer;
        switch (format) {
            case "json":
                importer = new JsonImporter();
                break;
            case "csv":
                importer = new CsvImporter();
                break;
            default:
                System.out.println("Неверный формат. Допустимые значения: json, csv.");
                return;
        }

        System.out.println("Введите путь к директории, откуда загружать данные:");
        String pathStr = scanner.nextLine().trim();
        Path path = Paths.get(pathStr);

        importerService.importData(importer, path);
        System.out.println("Импорт завершён.");
    }
}