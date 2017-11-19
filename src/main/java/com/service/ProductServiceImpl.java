package com.service;

import com.jpa.CategoryRepository;
import com.jpa.ProductRepository;
import com.model.Category;
import com.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public int addProduct(Product product) {
        productRepository.save(new HashSet<Product>() {{
            add(product);
        }});
        return product.getId();
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public void updateProduct(int id, Product product) {
        Product found = productRepository.findOne(id);
        found.setName(product.getName());
        found.setCategory(product.getCategory());
        found.setPrice(product.getPrice());
        productRepository.saveAndFlush(found);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.delete(id);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findOne(id);
    }

    @Override
    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }

    @Override
    public boolean exists(Product product) {
        return productRepository.exists(product.getId());
    }
}
