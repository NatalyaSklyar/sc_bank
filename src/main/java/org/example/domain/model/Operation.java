package org.example.domain.model;

import java.util.Date;
import java.util.UUID;

public class Operation extends FinanceEntity {
    private String type;
    private UUID bank_account_id;
    private Double amount;
    private Date date;
    private String description;
    private UUID category_id;

    public Operation(UUID id, String type, UUID bank_account_id, Double amount, Date date, String description, UUID category_id){
        this.id = id;
        this.type = type;
        this.bank_account_id = bank_account_id;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.category_id = category_id;
    }

    public Operation() {
        this.id = UUID.randomUUID();
        this.type = "";
        this.bank_account_id = UUID.randomUUID();
        this.amount = 0.0;
        this.date = new Date();
        this.description = "";
        this.category_id = UUID.randomUUID();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getBank_account_id() {
        return bank_account_id;
    }

    public void setBank_account_id(UUID bank_account_id) {
        this.bank_account_id = bank_account_id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getCategory_id() {
        return category_id;
    }

    public void setCategory_id(UUID category_id) {
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", bank_account_id=" + bank_account_id +
                ", amount=" + amount +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", category_id=" + category_id +
                '}';
    }
}