package org.example.domain.model;

import java.util.UUID;

public class Category extends FinanceEntity {
    public String type;
    public String name;

    public Category(UUID id, String type, String name){
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public Category() {
        this.id = UUID.randomUUID();
        this.type = "";
        this.name = "";
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
