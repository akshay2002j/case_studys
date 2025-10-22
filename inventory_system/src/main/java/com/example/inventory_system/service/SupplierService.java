package com.example.inventory_system.service;

import com.example.inventory_system.dao.SupplierDao;
import com.example.inventory_system.entity.Supplier;
import com.example.inventory_system.exception.ExceptionType;
import com.example.inventory_system.exception.SupplierException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierDao supplierDao;

    public SupplierService(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    public void addSupplier(Supplier supplier) {
        supplierDao.saveSupplier(supplier);
    }

    public void updateSupplier(Long id, String name, String email, String phone) {
        Supplier supplier = supplierDao.getSupplierById(id);
        if (supplier == null) {
            throw new SupplierException(ExceptionType.SUPPLIER_NOT_FOUND);
        }
        supplier.setName(name);
        supplier.setContactEmail(email);
        supplier.setPhone(phone);
        supplierDao.updateSupplier(supplier);
    }

    public void deleteSupplier(Long id) {
        supplierDao.deleteSupplier(id);
    }

    public Supplier getSupplierById(Long id) {
        return supplierDao.getSupplierById(id);
    }

    public Supplier getSupplierByName(String name) {
        return supplierDao.findByName(name);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierDao.getAllSuppliers();
    }

    public List<Object[]> getSupplierProductCounts() {
        return supplierDao.getSupplierProductCount();
    }

}
