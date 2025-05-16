package br.com.prodsys.product.repository;

import br.com.prodsys.product.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface ProductIRepository extends JpaRepository<Product, Long> {
}
