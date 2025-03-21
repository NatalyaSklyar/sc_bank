package org.example.infrastructure.di;

import org.example.app.console.ConsoleApp;
import org.example.app.console.ConsoleParser;
import org.example.domain.repositories.BankAccountRepository;
import org.example.domain.repositories.CategoryRepository;
import org.example.domain.repositories.OperationRepository;
import org.example.domain.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ConsoleParser consoleParser(BankAccountService bankAccountService,
                                       CategoryService categoryService,
                                       OperationService operationService,
                                       ExporterService exporterService,
                                       ImporterService importerService,
                                       AnalyticsService analyticsService
    ) {
        return new ConsoleParser(
                bankAccountService,
                categoryService,
                operationService,
                exporterService,
                importerService,
                analyticsService
        );
    }

    @Bean
    public ConsoleApp consoleApp(ConsoleParser consoleParser) {
        return new ConsoleApp(consoleParser);
    }

    @Bean
    public BankAccountRepository bankAccountRepository() {
        return new BankAccountRepository();
    }

    @Bean
    public CategoryRepository categoryRepository() {
        return new CategoryRepository();
    }

    @Bean
    public OperationRepository operationRepository() {
        return new OperationRepository();
    }

    @Bean
    public BankAccountService bankAccountService(BankAccountRepository bankAccountRepository) {
        return new BankAccountService(bankAccountRepository);
    }

    @Bean
    public CategoryService categoryService(CategoryRepository repository) {
        return new CategoryService(repository);
    }

    @Bean
    public OperationService operationService(
            OperationRepository operationRepository,
            BankAccountRepository accountRepository,
            CategoryRepository categoryRepository
    ) {
        return new OperationService(
                operationRepository,
                accountRepository,
                categoryRepository
        );
    }

    @Bean
    public ExporterService exporterService(
            OperationRepository operationRepository,
            BankAccountRepository accountRepository,
            CategoryRepository categoryRepository) {
        return new ExporterService(
                accountRepository,
                categoryRepository,
                operationRepository);
    }

    @Bean
    public ImporterService importerService(
            OperationRepository operationRepository,
            BankAccountRepository accountRepository,
            CategoryRepository categoryRepository) {
        return new ImporterService(
                accountRepository,
                categoryRepository,
                operationRepository);
    }

    @Bean
    public AnalyticsService analyticsService(OperationService operationService) {
        return new AnalyticsService(operationService);
    }
}