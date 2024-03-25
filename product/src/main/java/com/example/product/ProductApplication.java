package com.example.product;

import com.example.product.models.Product;
import com.example.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Autowired
	private ProductRepository productRepository;

//	@Bean
	CommandLineRunner commandLineRunner (){
		return args -> {
			Random random = new Random();
			for (int i = 0; i<5; i++){
				Product product = new Product("product name "+i, random.nextDouble());
				productRepository.save(product);
			}
		};
	}
}
