package com;

import com.jpa.CategoryRepository;
import com.jpa.ProductRepository;
import com.model.Category;
import com.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

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
	}
}
