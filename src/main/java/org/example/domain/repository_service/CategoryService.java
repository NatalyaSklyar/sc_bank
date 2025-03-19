package org.example.domain.repository_service;

import org.example.domain.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class CategoryService {
    private final Repository<Category> repository;

    public CategoryService(Repository<Category> repository) {
        this.repository = repository;
    }

    public Category createCategory(String type, String name) {
        Category category = new Category(UUID.randomUUID(), type, name);
        repository.save(category);
        return category;
    }

    public void updateCategory(Category category) {
        repository.update(category);
    }

    public void deleteCategory(UUID id) {
        repository.delete(id);
    }

    public Optional<Category> getCategoryById(UUID id) {
        return repository.findById(id);
    }

    public List<Category> getAllCategories() {
        return repository.findAll();
    }
}