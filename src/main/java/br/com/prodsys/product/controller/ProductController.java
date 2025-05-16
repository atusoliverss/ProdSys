package br.com.prodsys.product.controller;

import br.com.prodsys.mapper.ObjectMapperUntil;
import br.com.prodsys.product.dto.ProductDto;
import br.com.prodsys.product.dto.ProductDtoDeleteAndPut;
import br.com.prodsys.product.entities.Product;
import br.com.prodsys.product.service.ProductIService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
    private final ProductIService productIService;
    private final ObjectMapperUntil objectMapperUntil;

    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Product>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.productIService.findAll(pageable));
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid ProductDto product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productIService.save(objectMapperUntil.map(product, Product.class)));
    }

    @DeleteMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@RequestBody @Valid ProductDtoDeleteAndPut product) {

        productIService.delete((objectMapperUntil.map(product, Product.class)));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> update(@RequestBody @Valid ProductDtoDeleteAndPut product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productIService.update(objectMapperUntil.map(product, Product.class)));
    }
}
