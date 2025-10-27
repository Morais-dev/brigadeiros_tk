// src/main/java/br/com/brigadeirostk/adapter/memory/InMemorySaleRepository.java
package br.com.brigadeirostk.adapter.memory;

import br.com.brigadeirostk.domain.sale.Sale;
import br.com.brigadeirostk.port.SaleRepository;

import java.util.*;

public class InMemorySaleRepository implements SaleRepository {
    private final Map<String, Sale> db = new LinkedHashMap<>();

    @Override public Sale save(Sale s) { db.put(s.getId(), s); return s; }
    @Override public Optional<Sale> findById(String id) { return Optional.ofNullable(db.get(id)); }
    @Override public List<Sale> findAll() { return new ArrayList<>(db.values()); }
}
