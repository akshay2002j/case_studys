package com.example.inventory_system.service;


import com.example.inventory_system.dao.ProductDao;
import com.example.inventory_system.dao.SupplierDao;
import com.example.inventory_system.entity.Product;
import com.example.inventory_system.entity.Supplier;
import com.example.inventory_system.exception.ExceptionType;
import com.example.inventory_system.exception.ProductException;
import com.example.inventory_system.exception.SupplierException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

        private final ProductDao productDao;
        private final SupplierDao supplierDao;

        public ProductService(ProductDao productDao, SupplierDao supplierDao) {
            this.productDao = productDao;
            this.supplierDao = supplierDao;
        }

        public Product addProduct(Product product, String supplierName) {
            Supplier supplier = supplierDao.findByName(supplierName);
            if (supplier == null) throw new SupplierException(ExceptionType.SUPPLIER_NOT_FOUND);
            product.setSupplier(supplier);
            productDao.saveProduct(product);
            return product;
        }

        public Product updateStock(Long id, int changeQty) {
            Product product = productDao.getProductById(id);
            int newQty = product.getQuantity() + changeQty;
            if (newQty < 0) throw new ProductException(ExceptionType.PRODUCT_NOT_ENOUGH);
            product.setQuantity(newQty);
            productDao.updateProduct(product);
            return product;
        }

        public List<Product> lowStock(int threshold) {
            return productDao.findLowStockProducts(threshold);
        }

        public Double totalInventoryValue() {
            return productDao.getTotalInventoryValue();
        }

        public void deleteProduct(Long id) {
            productDao.deleteProduct(id);
        }
}


