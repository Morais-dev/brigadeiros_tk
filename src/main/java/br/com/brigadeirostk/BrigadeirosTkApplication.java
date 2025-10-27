// src/main/java/br/com/brigadeirostk/BrigadeirosTkApplication.java
package br.com.brigadeirostk;

import br.com.brigadeirostk.adapter.memory.*;
import br.com.brigadeirostk.domain.product.Product;
import br.com.brigadeirostk.domain.sale.SaleItem;
import br.com.brigadeirostk.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BrigadeirosTkApplication implements CommandLineRunner {

    private final ProductService productService;
    private final StockService stockService;
    private final ExpenseService expenseService;
    private final SaleService saleService;

    public BrigadeirosTkApplication() {
        // Adapters em memória (DIP: serviços dependem de abstrações, não de implementações)
        var productRepo = new InMemoryProductRepository();
        var stockRepo   = new InMemoryStockMovementRepository();
        var expenseRepo = new InMemoryExpenseRepository();
        var saleRepo    = new InMemorySaleRepository();

        // Serviços (SRP por serviço)
        this.productService = new ProductService(productRepo);
        this.stockService   = new StockService(productRepo, stockRepo);
        this.expenseService = new ExpenseService(expenseRepo);
        this.saleService    = new SaleService(productRepo, stockRepo, saleRepo);
    }

    public static void main(String[] args) {
        SpringApplication.run(BrigadeirosTkApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("=== Smoke test Etapa 6 (sem DB) ===");

        // 1) Cadastrar produto
        var brigadeiro = productService.create("Brigadeiro Gourmet", "UN", 3.50);
        var quindim    = productService.create("Quindim", "UN", 4.00);

        // 2) Entrada de estoque
        stockService.registerInbound(brigadeiro.getId(), 100, "Entrada inicial");
        stockService.registerInbound(quindim.getId(), 50, "Entrada inicial");

        // 3) Registrar uma despesa simples
        expenseService.register("Cacau em pó 1kg", 28.90);

        // 4) Efetuar venda (baixa de estoque + totalização)
        var venda = saleService.openSale();
        saleService.addItem(venda.getId(), new SaleItem(brigadeiro.getId(), 10, brigadeiro.getPrice()));
        saleService.addItem(venda.getId(), new SaleItem(quindim.getId(), 5, quindim.getPrice()));
        saleService.closeSale(venda.getId());

        // 5) Conferência rápida
        var estoqueBrigadeiro = stockService.getAvailableQuantity(brigadeiro.getId());
        var estoqueQuindim    = stockService.getAvailableQuantity(quindim.getId());

        System.out.println("Produtos: " + productService.findAll());
        System.out.println("Estoque (brigadeiro): " + estoqueBrigadeiro);
        System.out.println("Estoque (quindim): " + estoqueQuindim);
        System.out.println("Despesas: " + expenseService.findAll());
        System.out.println("Vendas: " + saleService.findAll());
        System.out.println("=== OK ===");
    }
}
