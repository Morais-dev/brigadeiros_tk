// src/main/java/br/com/brigadeirostk/port/SaleRepository.java
package br.com.brigadeirostk.port;

import br.com.brigadeirostk.domain.sale.Sale;
import java.util.List;
import java.util.Optional;

public interface SaleRepository {
    Sale save(Sale s);
    Optional<Sale> findById(String id);
    List<Sale> findAll();
}
