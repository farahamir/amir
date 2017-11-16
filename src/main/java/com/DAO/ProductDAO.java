package com.DAO;

import com.model.Category;
import com.model.Product;

import java.util.List;

public interface ProductDAO {

    int addProduct(Product product);
    List<Product> getProducts();

    int updateProduct(int id, Product product);

    int deleteProduct(int id);

    List<Category> getCategoryList();

    Product getProductById(int id);

    boolean isProductExists(Product product);
}
