package com.ing.store_management;

import com.ing.store_management.data.ProductRepository;
import com.ing.store_management.model.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class StoreManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreManagementApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(ProductRepository productRepository) {
		return args -> {
			Product product1 = Product.builder().name("product1").minQuantity(5).price(10).build();
			Product product2 = Product.builder().name("product2").minQuantity(15).price(5).build();
			Product product3 = Product.builder().name("product3").minQuantity(25).price(15).build();
			Product product4 = Product.builder().name("product4").minQuantity(50).price(20).build();
			productRepository.saveAll(List.of(product1, product2, product3, product4));
		};
	}
}
