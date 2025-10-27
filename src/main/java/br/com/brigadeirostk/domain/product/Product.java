// src/main/java/br/com/brigadeirostk/domain/product/Product.java
package br.com.brigadeirostk.domain.product;

import java.util.Objects;
import java.util.UUID;

public class Product {
    private final String id;
    private String name;
    private String unit;      // UN, CX, KG...
    private double price;     // preço unitário

    public Product(String name, String unit, double price) {
        this.id = UUID.randomUUID().toString();
        this.name = Objects.requireNonNull(name);
        this.unit = Objects.requireNonNull(unit);
        if (price < 0) throw new IllegalArgumentException("Preço inválido");
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getUnit() { return unit; }
    public double getPrice() { return price; }

    public void setName(String name) { this.name = Objects.requireNonNull(name); }
    public void setUnit(String unit) { this.unit = Objects.requireNonNull(unit); }
    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Preço inválido");
        this.price = price;
    }

    @Override public String toString() {
        return "Product{id='%s', name='%s', unit='%s', price=%.2f}"
                .formatted(id, name, unit, price);
    }
}
