// src/main/java/br/com/brigadeirostk/port/StockMovementRepository.java
package br.com.brigadeirostk.port;

import br.com.brigadeirostk.domain.stock.StockMovement;
import java.util.List;

public interface StockMovementRepository {
    StockMovement save(StockMovement m);
    List<StockMovement> findByProductId(String productId);
}
