package org.example.app.console.commands;

import org.example.domain.services.exporters.Exporter;
import org.example.domain.services.ExporterService;
import org.example.domain.services.exporters.JsonExporter;
import org.example.domain.services.exporters.CsvExporter;

import java.nio.file.Path;
import java.util.Scanner;

public class ExportCommand implements Command {

    private final ExporterService exporterService;
    private final Scanner scanner;

    public ExportCommand(ExporterService exporterService, Scanner scanner) {
        this.exporterService = exporterService;
        this.scanner = scanner;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("Выберите формат экспорта (json/csv): ");
        String format = scanner.nextLine().trim().toLowerCase();

        Exporter exporter;
        switch (format) {
            case "json":
                exporter = new JsonExporter();
                break;
            case "csv":
                exporter = new CsvExporter();
                break;
            default:
                System.out.println("Неверный формат. Допустимые значения: json, csv.");
                return;
        }

        System.out.println("Введите путь к директории для сохранения файлов:");
        String pathString = scanner.nextLine().trim();
        exporterService.exportData(exporter, Path.of(pathString));
        System.out.println("Экспорт завершён.");
    }
}