package com.example.inventory_system.controller;


import com.example.inventory_system.entity.Product;
import com.example.inventory_system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService  productService;


    @PostMapping("/")
    public ResponseEntity<Product> addProduct(@RequestBody Product product, @PathVariable String supplierName) {
        Product product1 = productService.addProduct(product,supplierName);
        return  new ResponseEntity<>(product1, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @PathVariable int quantity) {
       Product product = productService.updateStock(id,quantity);
       return  new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
         productService.deleteProduct(id);
         return  new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/lowstock/{threshold}")
    public ResponseEntity<List<Product>> getLowStockProducts(@PathVariable int threshold) {
        return new ResponseEntity<>(productService.lowStock(threshold), HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<?> totalInventoryValue () {
        Double value = productService.totalInventoryValue();
        return new ResponseEntity<>(new HashMap<>().put("Total Value",value), HttpStatus.OK);
    }

}
