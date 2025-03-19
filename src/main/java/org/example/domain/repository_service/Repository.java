package org.example.domain.repository_service;

import org.example.domain.model.FinanceEntity;

import java.util.*;

public abstract class Repository<T extends FinanceEntity> {
    private final HashMap<UUID, T> dataHashMap = new HashMap<>();

    protected void save(T entity) {
        dataHashMap.put(entity.getId(), entity);
    }

    protected void update(T entity) {
        if (dataHashMap.containsKey(entity.getId())) {
            dataHashMap.put(entity.getId(), entity);
        } else {
            save(entity);
            throw new NoSuchElementException("Account not found, new account is saved");
        }
    }

    protected void delete(UUID id) {
        dataHashMap.remove(id);
    }

    protected Optional<T> findById(UUID id) {
        return Optional.ofNullable(dataHashMap.get(id));

    }

    protected List<T> findAll() {
        return dataHashMap.values().stream().toList();
    }
}