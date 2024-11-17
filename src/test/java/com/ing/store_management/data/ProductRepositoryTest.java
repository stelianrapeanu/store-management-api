package com.ing.store_management.data;

import com.ing.store_management.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void saveProducts() {

        Product phone = Product.builder()
                .name("phone")
                .price(100)
                .minQuantity(10)
                .build();
        Product tablet = Product.builder()
                .name("tablet")
                .price(150)
                .minQuantity(5)
                .build();
        Product laptop = Product.builder()
                .name("laptop")
                .price(200)
                .minQuantity(20)
                .build();

        Product savedPhone = productRepository.save(phone);
        assertThat(savedPhone.getId()).isNotNull();
        Product savedTablet = productRepository.save(tablet);
        assertThat(savedTablet.getId()).isNotNull();
        Product savedLaptop = productRepository.save(laptop);
        assertThat(savedLaptop.getId()).isNotNull();

        Product fetchedPhone = productRepository.findById(savedPhone.getId()).get();
        assertThat(fetchedPhone.getName()).isEqualTo(phone.getName());
        assertThat(fetchedPhone.getPrice()).isEqualTo(phone.getPrice());

        List<Product> productList = productRepository.findByMinQuantity(20);
        assertThat(productList.get(0).getMinQuantity()).isEqualTo(20);

        List<Product> productByPriceBetween = productRepository.findByPriceBetween(101, 199);
        assertThat(productByPriceBetween.size()).isEqualTo(1);
    }
}