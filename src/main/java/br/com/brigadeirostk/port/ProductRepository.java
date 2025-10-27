// src/main/java/br/com/brigadeirostk/port/ProductRepository.java
package br.com.brigadeirostk.port;

import br.com.brigadeirostk.domain.product.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product p);
    Optional<Product> findById(String id);
    List<Product> findAll();
    void deleteById(String id);
}
