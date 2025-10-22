package com.example.inventory_system.controller;


import com.example.inventory_system.entity.Supplier;
import com.example.inventory_system.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;


    @PostMapping("/")
    public ResponseEntity<String> addSupplier(@RequestBody Supplier supplier) {
        supplierService.addSupplier(supplier);
        return ResponseEntity.ok("Supplier added successfully!");
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateSupplier(@PathVariable Long id,
                                                 @RequestBody Supplier updatedSupplier) {
        supplierService.updateSupplier(id,
                updatedSupplier.getName(),
                updatedSupplier.getContactEmail(),
                updatedSupplier.getPhone());
        return ResponseEntity.ok("Supplier updated successfully!");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok("Supplier deleted successfully!");
    }


    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }


    @GetMapping("/byName")
    public ResponseEntity<Supplier> getSupplierByName(@RequestParam String name) {
        return ResponseEntity.ok(supplierService.getSupplierByName(name));
    }


    @GetMapping("/all")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @GetMapping("/report/productCount")
    public ResponseEntity<List<Object[]>> getSupplierProductCountReport() {
        return ResponseEntity.ok(supplierService.getSupplierProductCounts());
    }
}
