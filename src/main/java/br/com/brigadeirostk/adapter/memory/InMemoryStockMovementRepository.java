// src/main/java/br/com/brigadeirostk/adapter/memory/InMemoryStockMovementRepository.java
package br.com.brigadeirostk.adapter.memory;

import br.com.brigadeirostk.domain.stock.StockMovement;
import br.com.brigadeirostk.port.StockMovementRepository;

import java.util.*;

public class InMemoryStockMovementRepository implements StockMovementRepository {
    private final Map<String, List<StockMovement>> byProduct = new HashMap<>();

    @Override public StockMovement save(StockMovement m) {
        byProduct.computeIfAbsent(m.getProductId(), k -> new ArrayList<>()).add(m);
        return m;
    }

    @Override public List<StockMovement> findByProductId(String productId) {
        return new ArrayList<>(byProduct.getOrDefault(productId, List.of()));
    }
}
