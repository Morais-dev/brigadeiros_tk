// src/main/java/br/com/brigadeirostk/adapter/memory/InMemoryExpenseRepository.java
package br.com.brigadeirostk.adapter.memory;

import br.com.brigadeirostk.domain.expense.Expense;
import br.com.brigadeirostk.port.ExpenseRepository;

import java.util.*;

public class InMemoryExpenseRepository implements ExpenseRepository {
    private final Map<String, Expense> db = new LinkedHashMap<>();

    @Override public Expense save(Expense e) { db.put(e.getId(), e); return e; }
    @Override public List<Expense> findAll() { return new ArrayList<>(db.values()); }
    @Override public Optional<Expense> findById(String id) { return Optional.ofNullable(db.get(id)); }
    @Override public void deleteById(String id) { db.remove(id); }
}
