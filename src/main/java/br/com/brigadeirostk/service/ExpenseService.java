// src/main/java/br/com/brigadeirostk/service/ExpenseService.java
package br.com.brigadeirostk.service;

import br.com.brigadeirostk.domain.expense.Expense;
import br.com.brigadeirostk.port.ExpenseRepository;

import java.util.List;

public class ExpenseService {
    private final ExpenseRepository repo;

    public ExpenseService(ExpenseRepository repo) { this.repo = repo; }

    public Expense register(String description, double amount) {
        return repo.save(new Expense(description, amount));
    }

    public List<Expense> findAll() { return repo.findAll(); }
    public void delete(String id) { repo.deleteById(id); }
}
