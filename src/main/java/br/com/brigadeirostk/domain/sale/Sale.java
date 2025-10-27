// src/main/java/br/com/brigadeirostk/domain/sale/Sale.java
package br.com.brigadeirostk.domain.sale;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Sale {
    public enum Status { OPEN, CLOSED }

    private final String id;
    private final LocalDateTime at;
    private Status status;
    private final List<SaleItem> items = new ArrayList<>();

    public Sale() {
        this.id = UUID.randomUUID().toString();
        this.at = LocalDateTime.now();
        this.status = Status.OPEN;
    }

    public String getId() { return id; }
    public LocalDateTime getAt() { return at; }
    public Status getStatus() { return status; }
    public List<SaleItem> getItems() { return items; }

    public void addItem(SaleItem item) {
        if (status == Status.CLOSED) {
            throw new IllegalStateException("Venda já encerrada");
        }
        items.add(item);
    }

    public void close() {
        if (items.isEmpty()) throw new IllegalStateException("Venda sem itens");
        this.status = Status.CLOSED;
    }

    public double getTotal() {
        return items.stream().mapToDouble(SaleItem::getTotal).sum();
    }

    @Override public String toString() {
        return "Sale{id='%s', status=%s, total=%.2f, at=%s}"
                .formatted(id, status, getTotal(), at);
    }
}
