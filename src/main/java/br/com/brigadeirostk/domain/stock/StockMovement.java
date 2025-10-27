// src/main/java/br/com/brigadeirostk/domain/stock/StockMovement.java
package br.com.brigadeirostk.domain.stock;

import java.time.LocalDateTime;
import java.util.UUID;

public class StockMovement {
    public enum Type { INBOUND, OUTBOUND }

    private final String id;
    private final String productId;
    private final int quantity;
    private final Type type;
    private final String reason;
    private final LocalDateTime at;

    public StockMovement(String productId, int quantity, Type type, String reason) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantidade deve ser > 0");
        this.id = UUID.randomUUID().toString();
        this.productId = productId;
        this.quantity = quantity;
        this.type = type;
        this.reason = reason;
        this.at = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public Type getType() { return type; }
    public String getReason() { return reason; }
    public LocalDateTime getAt() { return at; }
}
