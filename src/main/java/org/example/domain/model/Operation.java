package org.example.domain.model;

import java.util.Date;
import java.util.UUID;

public class Operation extends FinanceEntity {
    public String type;
    public UUID bank_account_id;
    public Double amount;
    public Date date;
    public String description;
    public UUID category_id;

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
        this.amount = (double) 0;
        this.date = new Date();
        this.description = "";
        this.category_id = UUID.randomUUID();
    }
}
