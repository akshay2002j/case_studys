package com.example.inventory_system.dao;

import com.example.inventory_system.entity.Product;

import java.util.List;

public interface ProductDao {
    void saveProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProduct(Long id);
    List<Product> findLowStockProducts(int threshold);
    Double getTotalInventoryValue();
}
