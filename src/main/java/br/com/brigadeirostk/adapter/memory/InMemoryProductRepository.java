// src/main/java/br/com/brigadeirostk/adapter/memory/InMemoryProductRepository.java
package br.com.brigadeirostk.adapter.memory;

import br.com.brigadeirostk.domain.product.Product;
import br.com.brigadeirostk.port.ProductRepository;

import java.util.*;

public class InMemoryProductRepository implements ProductRepository {
    private final Map<String, Product> db = new LinkedHashMap<>();

    @Override public Product save(Product p) { db.put(p.getId(), p); return p; }
    @Override public Optional<Product> findById(String id) { return Optional.ofNullable(db.get(id)); }
    @Override public List<Product> findAll() { return new ArrayList<>(db.values()); }
    @Override public void deleteById(String id) { db.remove(id); }
}
