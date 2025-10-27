// src/main/java/br/com/brigadeirostk/domain/expense/Expense.java
package br.com.brigadeirostk.domain.expense;

import java.time.LocalDateTime;
import java.util.UUID;

public class Expense {
    private final String id;
    private String description;
    private double amount;
    private final LocalDateTime at;

    public Expense(String description, double amount) {
        if (amount < 0) throw new IllegalArgumentException("Valor inválido");
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.amount = amount;
        this.at = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public LocalDateTime getAt() { return at; }

    public void setDescription(String description) { this.description = description; }
    public void setAmount(double amount) {
        if (amount < 0) throw new IllegalArgumentException("Valor inválido");
        this.amount = amount;
    }

    @Override public String toString() {
        return "Expense{id='%s', desc='%s', amount=%.2f, at=%s}"
                .formatted(id, description, amount, at);
    }
}
