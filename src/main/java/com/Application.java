package com;

import com.jpa.ProductRepository;
import com.model.Category;
import com.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	@Transactional
	public void run(String... strings) throws Exception {

		Category categoryA = new Category("Category A");
		Product product1 = new Product("Product 1", categoryA, 320.0);
		Product product2 = new Product("Product 2", categoryA, 600.0);
		Product product3 = new Product("Product 3", categoryA, 400.0);


		HashSet<Product> products1 = new HashSet<Product>(){{
			add(product1);
			add(product2);
			add(product3);
		}};
		categoryA.setProducts(products1);


		productRepository.save(new HashSet<Product>() {{
			add(product1);
			add(product2);
			add(product3);
		}});
		Product product4 = new Product("Product 4", categoryA, 6000.0);
		productRepository.save(new HashSet<Product>() {{
			add(product4);
		}});
		Category categoryB = new Category("Category B");
		Product product5 = new Product("Product 5", categoryB, 320.0);
		Product product6 = new Product("Product 6", categoryB, 600.0);
		HashSet<Product> products2 = new HashSet<Product>(){{
			add(product5);
			add(product6);
		}};
		categoryB.setProducts(products2);
		productRepository.save(new HashSet<Product>() {{
			add(product5);
			add(product6);
		}});
	}
}
