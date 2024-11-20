package com.ing.store_management.controller;

import com.ing.store_management.data.ProductRepository;
import com.ing.store_management.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/products", produces = "application/json")
@AllArgsConstructor
public class ProductController {

    private ProductRepository productRepository;

    @GetMapping
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> productById(@PathVariable("id") Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping(path="/{productId}", consumes="application/json")
    public Product putProduct(
            @PathVariable("productId") Long productId,
            @RequestBody Product product) {
        product.setId(productId);
        return productRepository.save(product);
    }

    @PatchMapping(path="/{productId}", consumes="application/json")
    public Product patchProduct(@PathVariable("productId") Long productId,
                                @RequestBody Product patch) {

        Product product = productRepository.findById(productId).get();
        if (patch.getName() != null) {
            product.setName(patch.getName());
        }
        if (patch.getPrice() != 0) {
            product.setPrice(patch.getPrice());
        }
        if (patch.getMinQuantity() != 0) {
            product.setMinQuantity(patch.getMinQuantity());
        }
        return productRepository.save(product);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("productId") Long productId) {
        try {
            productRepository.deleteById(productId);
        } catch (EmptyResultDataAccessException e) {}
    }
}
