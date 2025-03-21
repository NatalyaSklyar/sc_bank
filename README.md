# sc_bank

## Описание

Проект `sc_bank` представляет собой систему учета финансов, включающую создание и управление банковскими счетами, категориями и операциями. Система поддерживает экспорт и импорт данных в различных форматах, а также предоставляет аналитические функции.

## Структура проекта

### domain

Доменная модель

#### model

Папка с моделями данных:
- `BankAccount` - модель банковского счета
- `Category` - модель категории
- `Operation` - модель операции

#### repositories

Папка репозиториев - хранилища моделей данных:
- `BankAccountRepository` - репозиторий для банковских счетов
- `CategoryRepository` - репозиторий для категорий
- `OperationRepository` - репозиторий для операций

Методы репозиториев:
- `save` - сохранить модель
- `update` - обновить модель
- `delete` - удалить модель
- `findById` - найти модель по ID
- `findAll` - найти все модели

#### services

Папка сервисов, предоставляющих бизнес-логику:
- `BankAccountService` - сервис для работы с банковскими счетами
- `CategoryService` - сервис для работы с категориями
- `OperationService` - сервис для работы с операциями
- `ExporterService` - сервис для экспорта данных
- `ImporterService` - сервис для импорта данных
- `AnalyticsService` - сервис для аналитики

### app

Приложение

#### console

Консольное приложение:
- `ConsoleApp` - основной класс консольного приложения
- `ConsoleParser` - парсер команд консольного приложения

#### commands

Команды консольного приложения:
- `CreateAccountCommand` - команда создания счета
- `ShowAccountsCommand` - команда отображения счетов
- `CreateCategoryCommand` - команда создания категории
- `ShowCategoriesCommand` - команда отображения категорий
- `CreateOperationCommand` - команда создания операции
- `ShowOperationsCommand` - команда отображения операций
- `ExportCommand` - команда экспорта данных
- `ImportCommand` - команда импорта данных
- `GroupByCategoryCommand` - команда группировки операций по категориям
- `CalculateNetIncomeCommand` - команда вычисления чистого дохода

### infrastructure

Инфраструктура

#### di

Конфигурация зависимостей:
- `AppConfig` - конфигурационный класс Spring

## Принципы SOLID и GRASP

### Паттерны проектирования

1. **Dependency Injection (DI)**

    - **Файл:**
        - [`AppConfig`](src/main/java/org/example/infrastructure/di/AppConfig.java)

    - **Описание:**
      Паттерн DI используется для внедрения зависимостей в классы через конфигурационный класс `AppConfig`. Это позволяет легко управлять зависимостями и упрощает тестирование.

2. **Command**

    - **Файлы:**
        - [`Command`](src/main/java/org/example/app/console/commands/Command.java)
        - [`CreateAccountCommand`](src/main/java/org/example/app/console/commands/CreateAccountCommand.java)
        - [`ShowAccountsCommand`](src/main/java/org/example/app/console/commands/ShowAccountsCommand.java)
        - [`CreateCategoryCommand`](src/main/java/org/example/app/console/commands/CreateCategoryCommand.java)
        - [`ShowCategoriesCommand`](src/main/java/org/example/app/console/commands/ShowCategoriesCommand.java)
        - [`CreateOperationCommand`](src/main/java/org/example/app/console/commands/CreateOperationCommand.java)
        - [`ShowOperationsCommand`](src/main/java/org/example/app/console/commands/ShowOperationsCommand.java)
        - [`ExportCommand`](src/main/java/org/example/app/console/commands/ExportCommand.java)
        - [`ImportCommand`](src/main/java/org/example/app/console/commands/ImportCommand.java)
        - [`GroupByCategoryCommand`](src/main/java/org/example/app/console/commands/GroupByCategoryCommand.java)
        - [`CalculateNetIncomeCommand`](src/main/java/org/example/app/console/commands/CalculateNetIncomeCommand.java)
        - [`TimedCommand`](src/main/java/org/example/app/console/commands/timmedCommand/TimedCommand.java)

    - **Описание:**
      Паттерн Command используется для инкапсуляции запросов в виде объектов. Это позволяет параметризовать методы с помощью различных запросов, ставить запросы в очередь или логировать их.

3. **Visitor**

    - **Файлы:**
        - [`Exporter`](src/main/java/org/example/domain/services/exporters/Exporter.java)
        - [`JsonExporter`](src/main/java/org/example/domain/services/exporters/JsonExporter.java)
        - [`CsvExporter`](src/main/java/org/example/domain/services/exporters/CsvExporter.java)
        - [`AbstractImporter`](src/main/java/org/example/domain/services/importers/AbstractImporter.java)
        - [`JsonImporter`](src/main/java/org/example/domain/services/importers/JsonImporter.java)
        - [`CsvImporter`](src/main/java/org/example/domain/services/importers/CsvImporter.java)

4. **Repository**

    - **Файлы:**
        - [`Repository`](src/main/java/org/example/domain/repositories/Repository.java)
        - [`BankAccountRepository`](src/main/java/org/example/domain/repositories/BankAccountRepository.java)
        - [`CategoryRepository`](src/main/java/org/example/domain/repositories/CategoryRepository.java)
        - [`OperationRepository`](src/main/java/org/example/domain/repositories/OperationRepository.java)

5. **Service**

    - **Файлы:**
        - [`BankAccountService`](src/main/java/org/example/domain/services/BankAccountService.java)
        - [`CategoryService`](src/main/java/org/example/domain/services/CategoryService.java)
        - [`OperationService`](src/main/java/org/example/domain/services/OperationService.java)
        - [`ExporterService`](src/main/java/org/example/domain/services/ExporterService.java)
        - [`ImporterService`](src/main/java/org/example/domain/services/ImporterService.java)
        - [`AnalyticsService`](src/main/java/org/example/domain/services/AnalyticsService.java)

6. **Decorator**
    - **Файлы:**
        - [`TimedCommand`](src/main/java/org/example/app/console/commands/timmedCommand/TimedCommand.java)
        - [`Command`](src/main/java/org/example/app/console/commands/Command.java)

Эти паттерны помогают сделать код более гибким, масштабируемым и легким для сопровождения.