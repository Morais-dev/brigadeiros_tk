// src/main/java/br/com/brigadeirostk/service/ProductService.java
package br.com.brigadeirostk.service;

import br.com.brigadeirostk.domain.product.Product;
import br.com.brigadeirostk.port.ProductRepository;

import java.util.List;

public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) { this.repo = repo; }

    public Product create(String name, String unit, double price) {
        return repo.save(new Product(name, unit, price));
    }

    public List<Product> findAll() { return repo.findAll(); }
    public Product findById(String id) { return repo.findById(id).orElse(null); }

    public Product update(String id, String name, String unit, double price) {
        var p = repo.findById(id).orElseThrow();
        p.setName(name);
        p.setUnit(unit);
        p.setPrice(price);
        return repo.save(p);
    }

    public void delete(String id) { repo.deleteById(id); }
}
