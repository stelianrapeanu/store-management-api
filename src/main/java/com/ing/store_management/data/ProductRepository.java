package com.ing.store_management.data;

import com.ing.store_management.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByMinQuantity(int quantity);

    List<Product> findByPriceBetween(int minPrice, int maxPrice);
}
