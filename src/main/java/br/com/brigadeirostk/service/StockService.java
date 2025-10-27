// src/main/java/br/com/brigadeirostk/service/StockService.java
package br.com.brigadeirostk.service;

import br.com.brigadeirostk.domain.stock.StockMovement;
import br.com.brigadeirostk.port.ProductRepository;
import br.com.brigadeirostk.port.StockMovementRepository;

public class StockService {
    private final ProductRepository productRepo;
    private final StockMovementRepository stockRepo;

    public StockService(ProductRepository productRepo, StockMovementRepository stockRepo) {
        this.productRepo = productRepo;
        this.stockRepo = stockRepo;
    }

    public void registerInbound(String productId, int qty, String reason) {
        productRepo.findById(productId).orElseThrow();
        stockRepo.save(new StockMovement(productId, qty, StockMovement.Type.INBOUND, reason));
    }

    public void registerOutbound(String productId, int qty, String reason) {
        var available = getAvailableQuantity(productId);
        if (qty > available) throw new IllegalStateException("Estoque insuficiente");
        stockRepo.save(new StockMovement(productId, qty, StockMovement.Type.OUTBOUND, reason));
    }

    public int getAvailableQuantity(String productId) {
        return stockRepo.findByProductId(productId).stream()
                .mapToInt(m -> m.getType() == StockMovement.Type.INBOUND ? m.getQuantity() : -m.getQuantity())
                .sum();
    }
}
