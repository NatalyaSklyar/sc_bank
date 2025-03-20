package org.example.infrastructure.di;

import org.example.app.ConsoleApp;
import org.example.app.ConsoleParser;
import org.example.domain.repositories.BankAccountRepository;
import org.example.domain.repositories.CategoryRepository;
import org.example.domain.repositories.OperationRepository;
import org.example.domain.services.BankAccountService;
import org.example.domain.services.CategoryService;
import org.example.domain.services.OperationService;
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
    public CategoryRepository categoryRepository() {
        return new CategoryRepository();
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
}