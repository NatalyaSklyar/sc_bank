package org.example.infrastructure.di;

import org.example.app.ConsoleApp;
import org.example.app.ConsoleParser;
import org.example.domain.repository_service.BankAccountRepository;
import org.example.domain.repository_service.CategotyRepository;
import org.example.domain.repository_service.OperationRepository;
import org.example.domain.repository_service.BankAccountService;
import org.example.domain.repository_service.CategoryService;
import org.example.domain.repository_service.OperationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ConsoleParser consoleParser(BankAccountService bankAccountService,
            CategoryService categoryService,
            OperationService operationService
    ) {
        return new ConsoleParser(bankAccountService, categoryService, operationService);
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
    public CategotyRepository categoryRepository() {
        return new CategotyRepository();
    }

    @Bean
    public OperationRepository operationRepository() {
        return new OperationRepository();
    }

    @Bean
    public BankAccountService bankAccountService(BankAccountRepository repository) {
        return new BankAccountService(repository);
    }

    @Bean
    public CategoryService categoryService(CategotyRepository repository) {
        return new CategoryService(repository);
    }

    @Bean
    public OperationService operationService(OperationRepository repository) {
        return new OperationService(repository);
    }
}