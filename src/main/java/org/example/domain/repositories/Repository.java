package org.example.domain.repositories;

import org.example.domain.model.FinanceEntity;

import java.util.*;

public abstract class Repository<T extends FinanceEntity> {
    private final HashMap<UUID, T> dataHashMap = new HashMap<>();

    private UUID generateId() {
        UUID id = UUID.randomUUID();
        while (dataHashMap.containsKey(id)) {
            id = UUID.randomUUID();
        }
        return id;
    }

    public void save(T entity) {
        entity.setId(generateId());
        dataHashMap.put(entity.getId(), entity);
    }

    public void update(T entity) {
        if (dataHashMap.containsKey(entity.getId())) {
            dataHashMap.put(entity.getId(), entity);
        } else {
            throw new NoSuchElementException("Account not found");
        }
    }

    public void delete(UUID id) {
        dataHashMap.remove(id);
    }

    public Optional<T> findById(UUID id) {
        return Optional.ofNullable(dataHashMap.get(id));

    }

    public List<T> findAll() {
        return dataHashMap.values().stream().toList();
    }

    public boolean contains(UUID id) {
        return dataHashMap.containsKey(id);
    }
}