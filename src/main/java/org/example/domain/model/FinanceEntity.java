package org.example.domain.model;

import java.util.UUID;

public abstract class FinanceEntity {
    protected UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
