package br.com.prodsys.product.service;

import br.com.prodsys.product.entities.Product;
import br.com.prodsys.product.repository.ProductIRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductIService{

    private final ProductIRepository productIRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productIRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productIRepository.save(product);
    }

    @Override
    @Transactional
    public void delete(Product product) {
        productIRepository.delete(product);
    }

    @Override
    @Transactional
    public Product update(Product product) {
        return productIRepository.save(product);
    }
}
