package br.com.prodsys.product.service;

import br.com.prodsys.product.entities.Product;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface ProductIService {
    Page<Product> findAll(Pageable pageable);
    Product save(Product product);
    void delete(Product product);
    Product update(Product product);
}
