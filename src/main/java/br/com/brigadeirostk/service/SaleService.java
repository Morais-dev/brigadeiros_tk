// src/main/java/br/com/brigadeirostk/service/SaleService.java
package br.com.brigadeirostk.service;

import br.com.brigadeirostk.domain.sale.Sale;
import br.com.brigadeirostk.domain.sale.SaleItem;
import br.com.brigadeirostk.port.ProductRepository;
import br.com.brigadeirostk.port.SaleRepository;
import br.com.brigadeirostk.port.StockMovementRepository;

public class SaleService {
    private final ProductRepository productRepo;
    private final StockService stockService;
    private final SaleRepository saleRepo;

    public SaleService(ProductRepository productRepo,
                       StockMovementRepository stockRepo,
                       SaleRepository saleRepo) {
        this.productRepo = productRepo;
        this.stockService = new StockService(productRepo, stockRepo);
        this.saleRepo = saleRepo;
    }

    public Sale openSale() {
        var s = new Sale();
        return saleRepo.save(s);
    }

    public void addItem(String saleId, SaleItem item) {
        productRepo.findById(item.getProductId()).orElseThrow();
        var sale = saleRepo.findById(saleId).orElseThrow();
        sale.addItem(item);
        saleRepo.save(sale);
    }

    public void closeSale(String saleId) {
        var sale = saleRepo.findById(saleId).orElseThrow();
        // Baixa de estoque
        sale.getItems().forEach(i ->
            stockService.registerOutbound(i.getProductId(), i.getQuantity(), "Venda " + sale.getId())
        );
        sale.close();
        saleRepo.save(sale);
    }

    public Iterable<Sale> findAll() {
        return saleRepo.findAll();
    }
}
