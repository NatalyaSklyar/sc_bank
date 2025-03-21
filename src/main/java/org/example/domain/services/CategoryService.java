package org.example.domain.services;

import org.example.domain.model.Category;
import org.example.domain.repositories.Repository;

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

    public void updateCategoryType(UUID id, String type) {
        repository.findById(id).orElseThrow().setType(type);
    }

    public void updateCategoryName(UUID id, String name) {
        repository.findById(id).orElseThrow().setName(name);
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

    public boolean categoryExists(UUID id) {
        return repository.findById(id).isPresent();
    }
}