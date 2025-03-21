package org.example.domain.model;

import java.util.UUID;

public class Category extends FinanceEntity {
    private String type;
    private String name;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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