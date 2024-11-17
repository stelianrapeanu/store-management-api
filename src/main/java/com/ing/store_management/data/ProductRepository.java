package com.ing.store_management.data;

import com.ing.store_management.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByMinQuantity(int quantity);

    List<Product> findByPriceBetween(int minPrice, int maxPrice);
}
