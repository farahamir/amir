package com.service;

import com.DAO.ProductDAO;
import com.jpa.CategoryRepository;
import com.jpa.ProductRepository;
import com.model.Category;
import com.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductDAO dao;
    public int addProduct(Product product) {
        //return productRepository.saveAndFlush(product).getId();
        productRepository.save(new HashSet<Product>() {{
            add(product);
        }});
        return product.getId();
        //return dao.addProduct(product);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public void updateProduct(int id, Product product) {
       // int productId = dao.updateProduct(id,product);
       // product.setId(productId);
        Product found = productRepository.findOne(id);
        found.setName(product.getName());
        found.setCategory(product.getCategory());
        found.setPrice(product.getPrice());
        productRepository.saveAndFlush(found);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.delete(id);
        //dao.deleteProduct(id);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findOne(id);
        //return dao.getProductById(id);
    }

    @Override
    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }

    @Override
    public boolean exists(Product product) {
        return productRepository.exists(product.getId());
        //return dao.isProductExists(product);
    }
}
