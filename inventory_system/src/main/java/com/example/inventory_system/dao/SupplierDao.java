package com.example.inventory_system.dao;

import com.example.inventory_system.entity.Supplier;

import java.util.List;

public interface SupplierDao {
    void saveSupplier(Supplier supplier);
    void updateSupplier(Supplier supplier);
    void deleteSupplier(Long id);
    Supplier getSupplierById(Long id);
    Supplier findByName(String name);
    List<Supplier> getAllSuppliers();
    List<Object[]> getSupplierProductCount();
}
