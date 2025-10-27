// src/main/java/br/com/brigadeirostk/domain/sale/SaleItem.java
package br.com.brigadeirostk.domain.sale;

public class SaleItem {
    private String productId;
    private int quantity;
    private double unitPrice;

    public SaleItem() {}
    public SaleItem(String productId, int quantity, double unitPrice) {
        if (quantity <= 0) throw new IllegalArgumentException("Qtd inválida");
        if (unitPrice < 0) throw new IllegalArgumentException("Preço inválido");
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }

    public double getTotal() { return quantity * unitPrice; }
}
