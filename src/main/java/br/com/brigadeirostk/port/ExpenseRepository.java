// src/main/java/br/com/brigadeirostk/port/ExpenseRepository.java
package br.com.brigadeirostk.port;

import br.com.brigadeirostk.domain.expense.Expense;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository {
    Expense save(Expense e);
    List<Expense> findAll();
    Optional<Expense> findById(String id);
    void deleteById(String id);
}
